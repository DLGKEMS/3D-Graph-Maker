package com.Create_a_graph.easygraphing.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryJson implements JsonRepository{
    private  static List<Map<String, Object>> storeList = new ArrayList<Map<String, Object>>();
    @Override
    public Map<String, Object> save(String[] key, Object[] value, int size) {
        Map<String, Object> store = new HashMap<>();
        for(int i=0; i<size;i++) {
            store.put(key[i].trim(), value[i]);
        }
        storeList.add(store);
        return store;
    } // 키 배열과 value 배열 그리고 key의 갯수를 가져옴. Map객체를 만들어 storeList에 add해주는 메소드
    @Override
    public List<Map<String, Object>> getList() {
        return storeList;
    }
    public void clearStore(){
        storeList.clear();
    } // storeList 객체안에 내용을 다 지우는 메소드
}
