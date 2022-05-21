package com.example.springboot.BO.responses;

public class ScoreResponseObject extends GenericResponseObject {
    private int score;
    
    public ScoreResponseObject(int score){
        this.score = score;
    }
}
