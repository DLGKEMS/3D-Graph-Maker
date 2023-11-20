package com.Create_a_graph.easygraphing.service;


import com.Create_a_graph.easygraphing.Entity.KeyValueEntity;
import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CsvUtils {
    public static List<KeyValueEntity> readFromCsv(MultipartFile file) throws IOException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] header = csvReader.readNext(); // 첫 번째 라인을 헤더로 사용
            String[] nextLine;
            String[] columnValue = null;
            List<KeyValueEntity> entities = new ArrayList<>();

            MemoryJson memoryJson = new MemoryJson();
            Map<String, String> columns = new HashMap<>();
            MakeColumnMap MakeColumneMap = new MakeColumnMap();

            while ((nextLine = csvReader.readNext()) != null) {
                // nextLine 배열을 사용하여 KeyValueEntity를 생성
                KeyValueEntity entity = createEntity(header, nextLine);
                entities.add(entity);
                if(columnValue == null){columnValue = nextLine;}
            }

            for (int i = 0; i<header.length; i++){
                columns.put(header[i], columnValue[i]);
            } // {character=최애의갓근, address=경기도 안산시 단원구 고잔동 616-3, name=장영근, id=1, age=26}
            memoryJson.setColum(MakeColumneMap.creatColumMap(columns, header));

            return entities;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    private static KeyValueEntity createEntity(String[] header, String[] values) {
        KeyValueEntity entity = new KeyValueEntity();

        // 각 열의 값을 KeyValueEntity에 설정
        for (int i = 0; i < Math.min(header.length, values.length); i++) {
            entity.addColumn(header[i], values[i]);
        }

        return entity;
    }
}

//public class CsvUtils {
//        public static List<Map<String, String>> readFromCsv(MultipartFile file) throws IOException {
//            List<Map<String, String>> data = new ArrayList<>();
//            MemoryJson memoryJson = new MemoryJson();
//
//            CsvMapper csvMapper = new CsvMapper();
//            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader(); // 첫 번째 라인을 헤더로 사용
//
//            // MultipartFile을 List<Map<String, String>> 형태로 읽기
//            MappingIterator<Map<String, String>> mappingIterator = csvMapper
//                    .readerFor(Map.class)
//                    .with(csvSchema)
//                    .readValues(file.getInputStream());
//            List<Map<String, String>> csvData = mappingIterator.readAll();
//
//            StringBuilder str = new StringBuilder(csvData.get(0).keySet().toString());
//            str.deleteCharAt(0);
//            str.deleteCharAt(str.length()-1);
//            // 스트링 배열에 키 값을 ','로 split하여 저장
//            String[] key = String.valueOf(str).split(",");
//            for(int i=0;i < key.length;i++){
//                key[i] = key[i].trim();
//            }
//
//            Map<String, String> columType = new HashMap<>();
//            for (int i = 0; i<key.length; i++){
//                columType.put(key[i], csvData.get(0).get(key[i]).toString());
//            } // {character=최애의갓근, address=경기도 안산시 단원구 고잔동 616-3, name=장영근, id=1, age=26}
//            System.out.println("test columeType : " + columType);
//
//            MakeColumnMap makeColumeMap = new MakeColumnMap();
//            //makeColumeMap.creatColumMap(columType, key); // 컬럼들의 타입을 Map으로 만들어서 반환
//
//            memoryJson.setColum(makeColumeMap.creatColumMap(columType, key)); //컬럼 저장
//
//            return csvData;
//        }
//    }
