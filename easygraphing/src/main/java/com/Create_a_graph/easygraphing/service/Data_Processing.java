package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Data_Processing {
    public void data(String userqustion) throws JsonProcessingException {
        MemoryJson memoryJson = new MemoryJson();

        JSONArray jsonStoreArray = null;
        jsonStoreArray = memoryJson.getJsonArray();

        //System.out.println("JsonArray : " + Array);

        //JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject = null;

        Object[] jasonArrayList = null;
        jasonArrayList = new String[jsonStoreArray.length()]; //중복 허용한 아이템

        for(int i=0; i<jsonStoreArray.length(); i++){
            jsonObject = (JSONObject) jsonStoreArray.get(i);
            jasonArrayList[i]=(jsonObject.getString(userqustion));
        }

        Map<String, Integer> count = new HashMap<>(); // 갯수를 넣을 Map

        Object[] unreduplicationkey = null;
        unreduplicationkey = Arrays.stream(jasonArrayList).distinct().toArray();
        //ㄴ중복 허용 x > 그래프 높이 값을 얻기 위한 값을 추출할때 사용
        for(int i=0;i< unreduplicationkey.length;i++) {
            if(unreduplicationkey[i].toString() !=null && !unreduplicationkey[i].equals("")){
                count.put(unreduplicationkey[i].toString(), 0);
            }
        }
        //System.out.println("countkey : " + count.keySet());

        for(int i = 0; i<jsonStoreArray.length();i++) {
            jsonObject = (JSONObject) jsonStoreArray.get(i);
            for (int j = 0; j < count.size(); j++) {
                if (jsonObject.getString(userqustion).equals(unreduplicationkey[j])) {
                    if(count.get(unreduplicationkey[j].toString())== null){
                        continue;
                    }
                    count.put(unreduplicationkey[j].toString(), count.get(unreduplicationkey[j].toString()) + 1);
                }
            }
        }

        if(memoryJson.getMap() != null){
            memoryJson.storeMapClearing();
        }
        for(int i = 0; i<count.size(); i++){
            if(count.get(unreduplicationkey[i]) == null){
                continue;
            }
            memoryJson.setMap(unreduplicationkey[i].toString(), count.get(unreduplicationkey[i]));
        } //html으로 보낼 map을 만드는 코드


        //memoryJson.setJsonObject(new JSONObject(count));
    }
}