package com.example.springboot.BO.requests;


public class PlayerRequest {
    private String name;

    public PlayerRequest(){
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
}
