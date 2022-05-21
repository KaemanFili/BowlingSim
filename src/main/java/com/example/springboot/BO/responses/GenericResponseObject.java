package com.example.springboot.BO.responses;

public class GenericResponseObject {

    private String status;

    public GenericResponseObject(){
        status = "ok";
    }

    public void setErrorStatus(String errorMessage){
        status = "error: "+ errorMessage;
    }
    
}
