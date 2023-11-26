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
    //private Integer columnCount = 0;

//    public List<ColumnData> getColumnDataList(){
//        return columnDataList;
//    }
//    public Long getId(){return id;}
    public void addColumn(String columnName, String columnValue) {
        ColumnData columnData = new ColumnData(columnName, columnValue);
        columnDataList.add(columnData);
    }
}
