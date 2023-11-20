package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.KeyValueEntityRepository;
import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.DbCRUD;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
//    @Autowired
//    private Data_Processing filedata;
    @Autowired
    private KeyValueEntityRepository keyValueRepository;
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> handlePostRequest(@RequestBody DataClass data) throws JsonProcessingException {
        // 받은 데이터 처리

        DbCRUD dbCRUD = new DbCRUD(entityManager, keyValueRepository);
        dbCRUD.getNoConditionData(data.getSelectedValue(), data.getInput(), data.getselectedOperator());
        String type = "";
        System.out.println("성공");
        String selectedValue = data.getSelectedValue();
        String[] inputData = data.getInput();
        String option = data.selectedValue;

        // 클라이언트에 응답
        //return "Processed value: " + selectedValue;
        ObjectMapper objectMapper = new ObjectMapper();
        MemoryJson memoryJson = new MemoryJson();
        Map<String, Long> mapdata = memoryJson.getMap();
        String jsonString = objectMapper.writeValueAsString(mapdata);

        System.out.println(memoryJson.getMap());

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    public static class DataClass {
        private String selectedValue;
        private String[] input;
        private String[] selectedOperator;
        public String getSelectedValue() {
            return selectedValue;
        }
        public String[] getInput() {
            return input;
        }
        public String[] getselectedOperator(){ return selectedOperator; }


    }
}
