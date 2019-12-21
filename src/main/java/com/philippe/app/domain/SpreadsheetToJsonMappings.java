package com.philippe.app.domain;

import lombok.Data;

import java.util.Map;

@Data
public class SpreadsheetToJsonMappings {
    private int sheetIndex;
    private int headerRows;
    private Map<String, ColumnMapping> columnMappingMap;
}
