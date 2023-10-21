package com.Create_a_graph.easygraphing.repository;


import java.util.List;
import java.util.Map;


public interface JsonRepository {
    Map<String, Object> save(String[] key, Object[] value, int size);
    List<Map<String, Object>> getList();
    void clearStore();


}

