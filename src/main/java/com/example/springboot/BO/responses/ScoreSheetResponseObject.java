package com.example.springboot.BO.responses;

public class ScoreSheetResponseObject extends GenericResponseObject{
    private String sheet;

    public ScoreSheetResponseObject(String sheet){
        this.sheet = sheet;
    }
    
}
