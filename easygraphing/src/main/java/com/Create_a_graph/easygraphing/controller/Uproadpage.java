package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.repository.JsonRepository;
import com.Create_a_graph.easygraphing.repository.MemoryJson;
import com.Create_a_graph.easygraphing.service.FileUploadService;
import com.Create_a_graph.easygraphing.service.Data_Processing;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//    @PostMapping("selectColumeData")
//    @ResponseBody
//    public void colset(@RequestParam("selectedOption") String selectedOption){
//        MemoryJson memoryJson = new MemoryJson();
//        memoryJson.setColume(selectedOption);
//        //return "redirect:/frontTest";
//    }


//    @RequestMapping(value = "/createGraph")
//    public String test() {
//        try {
//            MemoryJson memoryJson = new MemoryJson();
//            filedata.data(memoryJson.getColume());
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/frontTest";
//    }

    @GetMapping("frontTest")
    public String showData(Model model) {
        MemoryJson memoryJson = new MemoryJson();
        Map<String, Integer> jsonData = new HashMap<>();
        jsonData = memoryJson.getMap();
        Map<String, String> colums = memoryJson.getColum();
        System.out.println(jsonData);
        model.addAttribute("jsonData", jsonData);
        model.addAttribute("columData", colums);
        return "frontTest";
    }

}
