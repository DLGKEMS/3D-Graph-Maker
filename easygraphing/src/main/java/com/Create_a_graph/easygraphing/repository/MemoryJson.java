package com.Create_a_graph.easygraphing.repository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemoryJson implements JsonRepository{
    private static JSONArray jsonArray;
    private static Map<String, String> culumList_type;
    private static JSONObject jsonObject;
    private static Map<String, Integer> storeMap = new HashMap<String, Integer>();
    private static String selectColume = "";

////////////////////////////////
    public void clearing(){
        jsonArray = null;
        culumList_type = null;
        jsonObject = null;
        storeMap.clear();
    }
    public void storeMapClearing(){
        storeMap.clear();
    }
//    public void resultClearing(){
//        jsonObject = null;
//    }
    public JSONArray getJsonArray(){
        return jsonArray;
    }
    public void setJsonArray(JSONArray jsonArray1){
        MemoryJson.jsonArray = jsonArray1;
        //System.out.println("JsonArray : " + MemoryJson.jsonArray);
    }
    public JSONObject getJsonObject() {
        return jsonObject;
    }
    public void setJsonObject(JSONObject jsonObject) {
        MemoryJson.jsonObject = jsonObject;
    }
    public void setColum(Map<String, String> columType){
        MemoryJson.culumList_type = columType;
    }
    public Map<String, String> getColum(){
        return culumList_type;
    }

    public void setMap(String key, int value){
        storeMap.put(key, value);
    }
    public Map<String, Integer> getMap(){
        return storeMap;
    }
}
