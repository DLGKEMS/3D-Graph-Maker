package com.Create_a_graph.easygraphing.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity //JPA (Java Persistence API) 엔터티로 정의
public class KeyValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "key_value_data", joinColumns = @JoinColumn(name = "entity_id"))
    private List<ColumnData> columnDataList = new ArrayList<>();
    private Integer columnCount = 0;
    // Getter, Setter, Constructors...

    public List<ColumnData> getColumnDataList(){
        return columnDataList;
    }
    public Long getId(){return id;}
    public void addColumn(String columnName, String columnValue) {
        ColumnData columnData = new ColumnData(columnName, columnValue);
        columnDataList.add(columnData);
    }
//    public boolean isValid() {
//        if (id == null || columnDataList == null || columnDataList.isEmpty()) {
//            return false;
//        }
//
//        for (ColumnData columnData : columnDataList) {
//            if (!columnData.isValid()) {
//                return false;
//            }
//        }
//
//        return true;
//    }
    // Other methods...

}


//package com.Create_a_graph.easygraphing.Entity;
//
//        import jakarta.persistence.*;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//@Entity
//public class KeyValueEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ElementCollection
//    @CollectionTable(name = "key_value_data", joinColumns = @JoinColumn(name = "entity_id"))
//    private List<ColumnData> columnDataList = new ArrayList<>();
//
//    // Getter, Setter, Constructors...
//
//    public void addColumn(String columnName, String columnValue) {
//        ColumnData columnData = new ColumnData(columnName, columnValue);
//        columnDataList.add(columnData);
//    }
//
//    // Other methods...
//
//}
//
//@Embeddable
//class ColumnData {
//    private String columnName;
//    private String columnValue;
//
//    // Getter, Setter, Constructors...
//
//    public ColumnData() {
//    }
//
//    public ColumnData(String columnName, String columnValue) {
//        this.columnName = columnName;
//        this.columnValue = columnValue;
//    }
//}
