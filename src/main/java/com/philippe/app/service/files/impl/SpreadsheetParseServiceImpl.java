package com.philippe.app.service.files.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.philippe.app.domain.ColumnDataType;
import com.philippe.app.domain.ColumnMapping;
import com.philippe.app.domain.SpreadsheetToJsonMappings;
import com.philippe.app.service.files.ParseService;
import com.philippe.app.util.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Slf4j
@Service
public class SpreadsheetParseServiceImpl implements ParseService {
    private final ObjectMapper objectMapper;
    private final SpreadsheetToJsonMappings spreadsheetToJsonMappings;

    private XSSFWorkbook workbook;

    private Function<Row, JsonNode> convertToJsonNode = this::mapToObjectNode;

    public SpreadsheetParseServiceImpl(final ObjectMapper objectMapper, final SpreadsheetToJsonMappings spreadsheetToJsonMappings) {
        this.objectMapper = objectMapper;
        this.spreadsheetToJsonMappings = spreadsheetToJsonMappings;
    }

    @Override
    public Stream<JsonNode> parse(final File file) throws IOException {
        return getRowStreamFromWorkbook(file)
                .filter(this::hasData)
                .map(convertToJsonNode);
    }

    @Override
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    /**
     * Produces an ObjectNode from a spreadsheet row.
     */
    private ObjectNode mapToObjectNode(final Row row) {
        final Map<String, ColumnMapping> configColumnMap = spreadsheetToJsonMappings.getColumnMappingMap();
        final ObjectNode objectNode = objectMapper.createObjectNode();
        configColumnMap.entrySet().forEach(rowToJsonConsumer(row, objectNode));
        return objectNode;
    }

    private Stream<Row> getRowStreamFromWorkbook(final File file) throws IOException {
        try {
            workbook = new XSSFWorkbook(file);
        } catch (InvalidFormatException e) {
            log.error("Error occurred while creating workbook.", e);
        }

        Stream<Row> result = Stream.empty();
        if (workbook != null) {
            result = StreamSupport.stream(workbook.getSheetAt(spreadsheetToJsonMappings.getSheetIndex()).spliterator(), false)
                    .skip(spreadsheetToJsonMappings.getHeaderRows());
        }

        return result;
    }

    private boolean hasData(final Row row) {
        boolean result = false;

        if (row != null && row.getLastCellNum() > 0) {
            for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && isNotBlank(cell.toString())) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    private Consumer<Map.Entry<String, ColumnMapping>> rowToJsonConsumer(final Row row, final ObjectNode objectNode) {
        return configColumnMapping -> {
            final String key = configColumnMapping.getKey();
            final ColumnMapping columnMapping = configColumnMapping.getValue();

            final Cell cell = row.getCell(columnMapping.getIndex());
            final ColumnDataType columnDataType = columnMapping.getColumnDataType();
            final Object cellValue = ParseUtil.cellToType(cell, columnDataType);

            switch(columnDataType) {
                case BOOLEAN:
                case INTEGER:
                    objectNode.putPOJO(key, cellValue);
                    break;
                default:
                    objectNode.put(key, cellValue.toString());
            }
        };
    }
}
