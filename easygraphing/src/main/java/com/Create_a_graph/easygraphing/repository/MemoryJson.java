package com.Create_a_graph.easygraphing.repository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemoryJson {
    private static JSONArray jsonArray;
    private static Map<String, String> culumList_type;
    private static JSONObject jsonObject;
    private static Map<String, Long> storeMap = new HashMap<String, Long>();
    private static String selectColume = "";

////////////////////////////////
    public void clearing(){
        jsonArray = null;
        culumList_type = null;
        jsonObject = null;
        storeMap.clear();
    }
//    public void storeMapClearing(){
//        storeMap.clear();
//    }
//
//    public JSONArray getJsonArray(){
//        return jsonArray;
//    }
//    public void setJsonArray(JSONArray jsonArray1){
//        MemoryJson.jsonArray = jsonArray1;
//        //System.out.println("JsonArray : " + MemoryJson.jsonArray);
//    }
    //public JSONObject getJsonObject() {
    //    return jsonObject;
    //}
    //public void setJsonObject(JSONObject jsonObject) {
       // MemoryJson.jsonObject = jsonObject;
    //}
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
