package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.service.FileUploadService;
import com.Create_a_graph.easygraphing.service.UsingData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//json저장 테스트용
import com.Create_a_graph.easygraphing.repository.MemoryJson;

@Controller
public class Uproadpage {
    @Autowired
    private FileUploadService fileUploadService;
    private UsingData filedata;
    //test
    private MemoryJson jsonstore;

    public Uproadpage(UsingData filedata) {
        this.filedata = filedata;
    } //< 이게 없으면 null오류남

    @RequestMapping(value = "/test")
    public String test() {
        try {
            Map<String, Object> complitMap = new HashMap<>();
            complitMap = filedata.data("자치구명");
            filedata.data("자치구명");
            System.out.println("compltMap : " + complitMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping
    public String index(){
        return "csvDD";
    }

    //저장된 데이터 리스트 출력
    @RequestMapping(value = "/hello")
    public String testfile(){
        fileUploadService.testfile();
        return "redirect:/";
    }

    @RequestMapping(value = "/frontTest")
    public String frontTest(){
        return "frontTest";
    }

    //파일 데이터 지우기
    @RequestMapping(value = "webtest")
    public String listclear(){
        fileUploadService.clearStore();
        return "/hello";
    }

    //파일 데이터 저장
    @PostMapping("/csvDD")
    //public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    public void fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.savefile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return "redirect:/";
    }
}

//
//    //public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        fileService.savefile(file);
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        //return "hello";
//        return "redirect:/";