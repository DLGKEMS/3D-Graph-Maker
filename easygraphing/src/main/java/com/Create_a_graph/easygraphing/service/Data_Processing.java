package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class Data_Processing {
    public void stringData(String userqustion, String[] condition) throws JsonProcessingException {
        MemoryJson memoryJson = new MemoryJson();

        JSONArray jsonStoreArray = null;
        jsonStoreArray = memoryJson.getJsonArray();

        JSONObject jsonObject = null;

        Object[] jasonArrayList = null;
        jasonArrayList = new String[jsonStoreArray.length()]; //중복 허용한 아이템

        if(condition.length>0) {
            for (int i = 0; i < jsonStoreArray.length(); i++) {
                jsonObject = (JSONObject) jsonStoreArray.get(i);
                for (int j = 0; j < condition.length; j++) {
                    if (jsonObject.getString(userqustion).equals(condition[j])){
                        jasonArrayList[i] = (jsonObject.getString(userqustion));
                    }
                }
            }
        }
        else {
            for (int i = 0; i < jsonStoreArray.length(); i++) {
                jsonObject = (JSONObject) jsonStoreArray.get(i);
                jasonArrayList[i] = (jsonObject.getString(userqustion));
            }
        }
        Map<String, Integer> count = new HashMap<>(); // 갯수를 넣을 Map


        Object[] unreduplicationkey = Arrays.stream(jasonArrayList).filter(Objects::nonNull).distinct().toArray();
        //unreduplicationkey = Arrays.stream(jasonArrayList).distinct().toArray();
        for(int i = 0; i<unreduplicationkey.length; i++){
            System.out.println("unreduplicationkey : " + unreduplicationkey[i]);
        }

        //ㄴ중복 허용 x > 그래프 높이 값을 얻기 위한 값을 추출할때 사용
        for(int i=0;i< unreduplicationkey.length;i++) {
            if(unreduplicationkey[i] !=null && !unreduplicationkey[i].equals("")){
                count.put(unreduplicationkey[i].toString(), 0);
            }
        }

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

        System.out.println("countkey : " + count.get("강남구"));

        if(memoryJson.getMap() != null){
            memoryJson.storeMapClearing();
        }
        for(int i = 0; i<count.size(); i++){
            if(count.get(unreduplicationkey[i]) == null){
                continue;
            }
            memoryJson.setMap(unreduplicationkey[i].toString(), count.get(unreduplicationkey[i]));
        } //html으로 보낼 map을 만드는 코드

        Map<String, Integer> test = memoryJson.getMap();

        //memoryJson.setJsonObject(new JSONObject(count));
    }
    public void integerData(String userqustion, String[] condition) throws JsonProcessingException {
        MemoryJson memoryJson = new MemoryJson();

        JSONArray jsonStoreArray = null;
        jsonStoreArray = memoryJson.getJsonArray();

        JSONObject jsonObject = null;

        Object[] jasonArrayList = null;
        jasonArrayList = new String[jsonStoreArray.length()]; //중복 허용한 아이템

        if(condition.length>0) {
            for (int i = 0; i < jsonStoreArray.length(); i++) {
                jsonObject = (JSONObject) jsonStoreArray.get(i);
                for (int j = 0; j < condition.length; j++) {
                    if (jsonObject.getString(userqustion).equals(condition[j])){
                        jasonArrayList[i] = (jsonObject.getString(userqustion));
                    }
                }
            }
        }
        else {
            for (int i = 0; i < jsonStoreArray.length(); i++) {
                jsonObject = (JSONObject) jsonStoreArray.get(i);
                jasonArrayList[i] = (jsonObject.getString(userqustion));
            }
        }
        Map<String, Integer> count = new HashMap<>(); // 갯수를 넣을 Map


        Object[] unreduplicationkey = Arrays.stream(jasonArrayList).filter(Objects::nonNull).distinct().toArray();
        //unreduplicationkey = Arrays.stream(jasonArrayList).distinct().toArray();
        for(int i = 0; i<unreduplicationkey.length; i++){
            System.out.println("unreduplicationkey : " + unreduplicationkey[i]);
        }

        //ㄴ중복 허용 x > 그래프 높이 값을 얻기 위한 값을 추출할때 사용
        for(int i=0;i< unreduplicationkey.length;i++) {
            if(unreduplicationkey[i] !=null && !unreduplicationkey[i].equals("")){
                count.put(unreduplicationkey[i].toString(), 0);
            }
        }

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

        System.out.println("countkey : " + count.get("강남구"));

        if(memoryJson.getMap() != null){
            memoryJson.storeMapClearing();
        }
        for(int i = 0; i<count.size(); i++){
            if(count.get(unreduplicationkey[i]) == null){
                continue;
            }
            memoryJson.setMap(unreduplicationkey[i].toString(), count.get(unreduplicationkey[i]));
        } //html으로 보낼 map을 만드는 코드

        Map<String, Integer> test = memoryJson.getMap();

        //memoryJson.setJsonObject(new JSONObject(count));
    }
}