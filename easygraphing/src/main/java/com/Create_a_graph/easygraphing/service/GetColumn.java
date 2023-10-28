package com.Create_a_graph.easygraphing.service;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import org.springframework.stereotype.Service;

@Service
public class GetColumn {
    public void getColumn(){
        MemoryJson memoryJson = new MemoryJson();
        System.out.println("JsonObject : " + memoryJson.getJsonObject());
        String[] test = memoryJson.getColum();
        for(int i =0; i<test.length;i++){
            System.out.println(test[i]);
        }
    }
}
