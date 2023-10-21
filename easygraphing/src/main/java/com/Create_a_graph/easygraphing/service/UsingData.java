package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsingData {
    public void data(String userqustion) throws JsonProcessingException {
        MemoryJson memoryJson = new MemoryJson();
        List<Map<String, Object>> storeData = memoryJson.getList();
        //System.out.println("csvData : " + csvData);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStoreData = objectMapper.writeValueAsString(storeData);
        //System.out.println("JsonData : " + jsonData);

        JSONArray jsonStoreArray = new JSONArray(jsonStoreData);
        //System.out.println("JsonArray : " + Array);

        JSONObject jsonObject = new JSONObject();
        Object[] jasonArrayList = new String[jsonStoreArray.length()]; //중복 허용한 아이템

        for(int i=0; i<jsonStoreArray.length(); i++){
            jsonObject = (JSONObject) jsonStoreArray.get(i);
            jasonArrayList[i]=(jsonObject.getString(userqustion));
            //System.out.println("jason 리스트 : " + jasonArrayList[i]);
        }

        Map<String, Integer> count = new HashMap<>(); // 갯수를 넣을 Map

        Object[] unreduplicationkey = Arrays.stream(jasonArrayList).distinct().toArray();
        //ㄴ중복 허용 x > 그래프 높이 값을 얻기 위한 값을 추출할때 사용
        for(int i=0;i< unreduplicationkey.length;i++) {
            //System.out.println("완성 : " + newjasonArrayList[i]);
            count.put(jasonArrayList[i].toString(),0);
        }

        for(int i = 0; i<jsonStoreArray.length();i++){
            jsonObject = (JSONObject) jsonStoreArray.get(i);
            for(int j = 0; j<unreduplicationkey.length; j++) {
                if(jsonObject.getString(userqustion).equals(unreduplicationkey[j])){
                    count.put(unreduplicationkey[j].toString(), count.get(unreduplicationkey[j].toString())+ 1);
                }
            }
        }
        System.out.println("Result : " + count);

//jsonObject.getString(userqustion) == newjasonArrayList[j]
        //Set<Map<String, Object>> set = new HashSet<Map<String, Object>>((Collection<? extends Map<String, Object>>) jsonObject.get(userqustion));

        //System.out.println(set.size());
        // System.out.println(set);
//        for(int i=0; i<Array.length(); i++){
//            jsonObject = (JSONObject) Array.get(i);
//
//            System.out.println(jsonObject.get(userqustion));
//        }
//        for(int i=0; i<Array.length(); i++){
//            jsonObject = (JSONObject) Array.get(i);
//            String exdata = jsonObject.getString(userqustion);
//            if(exdata.equals("상가지역")) {
//                System.out.println(jsonObject.getString("도로명"));
//                System.out.println(exdata + "\n------------------------------");
//            }
//        }
        //System.out.println(jsonObject);
    }

}

// System.out.println("array의 get1" + Array.get(i));
//if(jsonObject.equals(condition)){
//System.out.println(jsonObject.getJSONObject("상가지역"));