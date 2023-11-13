package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/showMe")
public class showGraphcontroller {
    @PostMapping
    public Map<String, Integer> handleRequest() {
        System.out.println("showMe에 들어옴 ");
        MemoryJson memoryJson = new MemoryJson();

        Map<String, Integer> responseData = memoryJson.getMap();
        return responseData;
    }
}
