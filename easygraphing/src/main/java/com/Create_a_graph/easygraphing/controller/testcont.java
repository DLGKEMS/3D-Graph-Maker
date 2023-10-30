package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.GetColumn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class testcont {
    //private GetColumn getColumn;
    //public testcont(GetColumn getColumn){this.getColumn = getColumn;}
    private GetColumn getColumn;
    public testcont (GetColumn getColumn) {
        this.getColumn = getColumn;
    }

//    @GetMapping("/data")
//    public String showData(Model model) {
//        MemoryJson memoryJson = new MemoryJson();
//        Map<String, Integer> jsonData = new HashMap<>();
//        jsonData = memoryJson.getMap();
//        System.out.println(jsonData);
////        jsonData.put("동대문구", 80);
////        jsonData.put("중랑구", 93);
////        jsonData.put("은평구", 233);
////        jsonData.put("강남구", 976);
//        model.addAttribute("jsonData", jsonData);
//        return "data";
//    }
}
