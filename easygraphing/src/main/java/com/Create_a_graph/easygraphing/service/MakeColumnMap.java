package com.Create_a_graph.easygraphing.service;

import java.util.HashMap;
import java.util.Map;
public class MakeColumnMap {
    public Map<String, String> creatColumMap(Map<String, String> columes, String[] key){

        Map<String, String> columeTypeMap = new HashMap<>();
        for(int i = 0; i< key.length;i++){
            if(columes.get(key[i]).toString().matches("[0-9]+")){
                columeTypeMap.put(key[i], "Integer");
            }
            else{
                columeTypeMap.put(key[i], "String");
            }
        }
        System.out.println("columeTypeMap : " + columeTypeMap);
        return columeTypeMap;
    }
}
