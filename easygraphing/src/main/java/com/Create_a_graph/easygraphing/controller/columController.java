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
        dbCRUD.getConditionData(data.getSelectedValue(), data.getInput(), data.getLogicSelect(), data.getEqualSelect(), data.getColumnSelect(), data.getType());
        System.out.println("성공");
        for(int i = 0; i< data.getType().length; i++){
            System.out.println(data.getType());
        }

        // 클라이언트에 응답
        //return "Processed value: " + selectedValue;
        ObjectMapper objectMapper = new ObjectMapper();
        MemoryJson memoryJson = new MemoryJson();
        Map<String, Long> mapdata = memoryJson.getMap();
        String jsonString = objectMapper.writeValueAsString(mapdata);
        // 클라이언트에 보내서 그래프를 만들 Map 데이터
        System.out.println(memoryJson.getMap());

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    public static class DataClass {
        private String selectedValue;
        private String[] input;
        private String[] integerInput;
        private String[] logicSelect;
        private String[] equalSelect;
        private String[] columnSelect;
        private String[] type;

        public String getSelectedValue() {
            return selectedValue;
        }
        public String[] getInput() {
            return input;
        }
        public String[] getIntegerInput(){return integerInput;}
        public String[] getLogicSelect(){ return logicSelect; }
        public String[] getEqualSelect(){ return equalSelect; }
        public String[] getColumnSelect(){return columnSelect;}
        public String[] getType(){return type;}
    }
}
