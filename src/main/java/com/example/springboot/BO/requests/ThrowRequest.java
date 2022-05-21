package com.example.springboot.BO.requests;

public class ThrowRequest extends GenericRequest  {
    private String id;
    private int framdeIndex;
    private int ballIndex;
    private int score;
    
    public ThrowRequest(){}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getFramdeIndex() {
        return framdeIndex;
    }
    public void setFramdeIndex(int framdeIndex) {
        this.framdeIndex = framdeIndex;
    }
    public int getBallIndex() {
        return ballIndex;
    }
    public void setBallIndex(int ballIndex) {
        this.ballIndex = ballIndex;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }


}
