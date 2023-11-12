package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.Data_Processing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/your-endpoint")
public class columController {
    @Autowired
    private Data_Processing filedata;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> handlePostRequest(@RequestBody DataClass data) throws JsonProcessingException {
        // 받은 데이터 처리
        String selectedValue = data.getSelectedValue();
        filedata.data(selectedValue);
        // 클라이언트에 응답
        //return "Processed value: " + selectedValue;
        ObjectMapper objectMapper = new ObjectMapper();
        MemoryJson memoryJson = new MemoryJson();
        Map<String, Integer> mapdata = memoryJson.getMap();
        String jsonString = objectMapper.writeValueAsString(mapdata);
        
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    public static class DataClass {
        private String selectedValue;

        public String getSelectedValue() {
            return selectedValue;
        }

        public void setSelectedValue(String selectedValue) {
            this.selectedValue = selectedValue;
        }
    }
}
