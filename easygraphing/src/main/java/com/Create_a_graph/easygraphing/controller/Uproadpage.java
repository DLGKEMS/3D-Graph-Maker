package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@Controller
public class Uproadpage {
    @Autowired
    private FileUploadService fileService;

        @GetMapping
        public String index(){
            return "csvDD";
        }

        @PostMapping("/csvDD")
        //public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
            try {
                fileService.savefile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");

            //return "hello";
            return "redirect:/";
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