package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class JsonConversion {
    public void createJson(MultipartFile file) throws Exception {
        List<String[]> csvData = readCSV(file);

        // List<String[]>을 JSON 형식으로 변환
        String jsonData = convertToJson(csvData);
        MemoryJson memoryJson = new MemoryJson();
        memoryJson.setJson(jsonData);
        System.out.println(jsonData);

    }

    private List<String[]> readCSV(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return reader.lines()
                    .limit(30)
                    .map(line -> line.split(","))  // CSV의 구분자에 따라 수정
                    .collect(Collectors.toList());
        }
    }
    private String convertToJson(List<String[]> csvData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(csvData);
    }
}

