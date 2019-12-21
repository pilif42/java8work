package com.philippe.app.util;

import com.philippe.app.domain.ColumnDataType;
import org.apache.poi.ss.usermodel.Cell;

import java.util.function.BiFunction;

import static java.lang.String.format;

public class ParseUtil {
    private static final BiFunction<Cell, ColumnDataType, Object> booleanConverterFunction =
            (cell, type) -> type == ColumnDataType.BOOLEAN ?
                    cell.getBooleanCellValue() :
                    String.valueOf(cell.getBooleanCellValue());

    private static final BiFunction<Cell, ColumnDataType, Object> stringConverterFunction =
            (cell, type) -> type == ColumnDataType.STRING ?
                    cell.getStringCellValue().trim() :
                    Integer.parseInt(cell.getStringCellValue());

    private static final BiFunction<Cell, ColumnDataType, Object> numericConverterFunction = (cell, type) -> {
        switch (type) {
            case INTEGER:
                return cell.getNumericCellValue();
            case EFF_DATE:
                return null; // TODO using cell.getDateCellValue()
            case EXP_DATE:
                return null; // TODO using cell.getDateCellValue()
            default:
                return String.valueOf((int) cell.getNumericCellValue()).trim();
        }
    };

    private ParseUtil() {

    }

    public static Object cellToType(Cell cell, ColumnDataType columnDataType) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                return booleanConverterFunction.apply(cell, columnDataType);
            case STRING:
                return stringConverterFunction.apply(cell, columnDataType);
            case NUMERIC:
                return numericConverterFunction.apply(cell, columnDataType);
            default:
                throw new UnsupportedOperationException(format("We do not support yet the type: %s", cell.getCellTypeEnum().name()));
        }
    }
}
