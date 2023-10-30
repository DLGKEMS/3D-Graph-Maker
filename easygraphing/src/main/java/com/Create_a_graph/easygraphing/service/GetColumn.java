package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class GetColumn {
    @GetMapping("/testget")
    public JSONObject getColumn(){
        MemoryJson memoryJson = new MemoryJson();
        JSONObject returnjson = memoryJson.getJsonObject();

        return returnjson;
    }
}


//    MemoryJson memoryJson = new MemoryJson();
//        System.out.println("JsonObject : " + memoryJson.getJsonObject());
//                String[] test = memoryJson.getColum();
//                for(int i =0; i<test.length;i++){
//        System.out.println(test[i]);
//        }