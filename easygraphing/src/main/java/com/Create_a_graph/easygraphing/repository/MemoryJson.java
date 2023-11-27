package com.Create_a_graph.easygraphing.repository;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemoryJson {
    private static String json;
    private static Map<String, String> culumList_type;
    private static JSONObject jsonObject;
    private static Map<String, Long> storeMap = new HashMap<String, Long>();
    private static String selectColume = "";


////////////////////////////////
    public void clearing(){
        json = null;
        culumList_type = null;
        jsonObject = null;
        storeMap.clear();
    }
    public void setJson(String jsons){
        json = jsons;
    }
    public String getJson(){
        return json;
    }
    public void setColum(Map<String, String> columType){
        MemoryJson.culumList_type = columType;
    }
    public Map<String, String> getColum(){
        return culumList_type;
    }

    public void setMap(String key, Long value){
        storeMap.put(key, value);
    }
    public Map<String, Long> getMap(){
        return storeMap;
    }
}
