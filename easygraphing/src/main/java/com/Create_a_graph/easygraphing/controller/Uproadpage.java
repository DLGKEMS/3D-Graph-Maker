package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.FileUploadService;
import com.Create_a_graph.easygraphing.service.Data_Processing;
import com.Create_a_graph.easygraphing.service.GetColumn;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Uproadpage {
    @Autowired
    private FileUploadService fileUploadService;
    private Data_Processing filedata;
    //test

    public Uproadpage(Data_Processing filedata) {
        this.filedata = filedata;
    } //< 이게 없으면 null오류남

    //@RequestMapping
    @GetMapping
    public String index(){
        return "csvDD";
    }

    //파일 데이터 저장
    @PostMapping("/csvDD")
    //public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            MemoryJson memoryJson = new MemoryJson();
            if(memoryJson.getJsonArray() != null){
                memoryJson.clearing();
            }
            fileUploadService.savefile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/frontTest";
    }

    @RequestMapping(value = "/createGraph")
    public String test() {
        try {
            filedata.data("자치구명");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/frontTest";
    }

//    @PostMapping("/createGraph")
//    public String graphShow(@RequestParam("inputGroupSelect02") String value){
//        try {
//            filedata.data(value);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/frontTest";
//    }//

    @GetMapping("showGraph")
    public String showData(Model model) {
        MemoryJson memoryJson = new MemoryJson();
        Map<String, Integer> jsonData = new HashMap<>();
        jsonData = memoryJson.getMap();
        String[] colums = memoryJson.getColum();
        model.addAttribute("jsonData", jsonData);
        model.addAttribute("columData", colums);
        return "redirect:/frontTest";
    }
}

//    @RequestMapping(value = "/showGraph")
//    public String test() {
//        try {
//            filedata.data("자치구명");
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/frontTest";
//    }