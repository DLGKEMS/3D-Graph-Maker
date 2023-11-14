package com.Create_a_graph.easygraphing.service;

public class SearchType {
    public String search(String data){
        if(data.matches("[0-9]+")){
            return "Integer";
        }
        else{
            return "String";
        }
    }
}
