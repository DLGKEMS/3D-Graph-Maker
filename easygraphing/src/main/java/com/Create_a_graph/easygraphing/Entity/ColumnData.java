package com.Create_a_graph.easygraphing.Entity;

import jakarta.persistence.Embeddable;

@Embeddable

public class ColumnData {
    private String columnName;
    private String columnValue;


    // Getter, Setter, Constructors...

    public ColumnData(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;

    }

//    public boolean isValid() {
//        return columnName != null && !columnName.isEmpty() &&
//                columnValue != null && !columnValue.isEmpty();
//    }

    public ColumnData() {

    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }
}
