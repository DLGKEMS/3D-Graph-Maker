package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.fasterxml.jackson.databind.MappingIterator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadService {

    public void testfile(){
        MemoryJson memoryJson = new MemoryJson();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = memoryJson.getList();
        System.out.println("완성본 : " + list);
    }

    //파일 데이터 지우기
    public void clearStore(){
        MemoryJson memoryJson = new MemoryJson();
        memoryJson.clearStore();
    }

    //파일 데이터 저장
    public void savefile(MultipartFile file) throws IOException {
        try {
            // Jackson CSV Mapper 생성
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader(); // 첫 번째 라인을 헤더로 사용

            // MultipartFile을 List<Map<String, String>> 형태로 읽기
            MappingIterator<Map<String, String>> mappingIterator = csvMapper
                    .readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(file.getInputStream());
            List<Map<String, String>> csvData = mappingIterator.readAll();

            // 파일의 키 값(컬럼)을 StringBuilder로 받아 양끝('[]')을 제거
            StringBuilder str = new StringBuilder(csvData.get(0).keySet().toString());
            str.deleteCharAt(0);
            str.deleteCharAt(str.length()-1);

            // 스트링 배열에 키 값을 ','로 split하여 저장
            //
            String[] key = String.valueOf(str).split(",");
            Object[] value = new Object[key.length];
            MemoryJson memoryJson = new MemoryJson();
            for(int i=0;i<csvData.size();i++){
                for (int j =0; j<key.length; j++){
                    value[j] = csvData.get(i).get(key[j].trim());
                }
                memoryJson.save(key,value, key.length);
            } // 파일의 크기만큼 반복해 파일의 1번줄 부터 n줄까지 1줄씩 save함수를 호출해 storeList에 저장
        } catch (Exception e) {
            e.printStackTrace();
            //return null; // 예외 처리
        }
    }
}


