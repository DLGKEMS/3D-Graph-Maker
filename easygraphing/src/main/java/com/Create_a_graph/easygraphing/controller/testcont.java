package com.Create_a_graph.easygraphing.controller;

import com.Create_a_graph.easygraphing.service.Data_Processing;
import com.Create_a_graph.easygraphing.service.GetColumn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testcont {
    private GetColumn getColumn;
    public testcont(GetColumn getColumn){this.getColumn = getColumn;}
    @RequestMapping(value = "/hello")
    public String testfile(){
        getColumn.getColumn();
        return "/hello";
    }
}
