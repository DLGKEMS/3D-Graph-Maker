package com.Create_a_graph.easygraphing.repository;


import org.json.JSONArray;

import java.util.List;
import java.util.Map;


public interface JsonRepository {
    Map<String, Object> save(String[] key, Object[] value, int size);
    List<Map<String, Object>> getList();
    Map completeJson(String[] key, Object[] value, int size);

    void clearStore();


}

