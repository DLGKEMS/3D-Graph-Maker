package com.Create_a_graph.easygraphing.repository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemoryJson implements JsonRepository{
    private static JSONArray jsonArray;
    private static String[] culumList;
    private static JSONObject jsonObject;
    private static Map<String, Integer> storeMap = new HashMap<String, Integer>();

////////////////////////////////
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
        System.out.println("JsonObject : " + MemoryJson.jsonObject);
    }

    public void setColum(String[] keys){
        String[] clonkey = new String[keys.length];
        for(int i=0;i < keys.length;i++){
            clonkey[i] = keys[i].trim();
        }
        MemoryJson.culumList = clonkey;
//        for(int i=0;i<MemoryJson.culumList.length;i++) {
//            System.out.println("Colum" + i + " : " + MemoryJson.culumList[i]);
//        }
    }
    public String[] getColum(){
        return culumList;
    }

    public void setMap(String key, int value){
        storeMap.put(key, value);
    }
    public Map<String, Integer> getMap(){
        return storeMap;
    }
}
