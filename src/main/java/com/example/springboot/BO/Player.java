package com.example.springboot.BO;

public class Player {
    private BowlingGame game;
    
    public void startGame(){
        game= new BowlingGame();
    }

    public BowlingGame getGame(){
        return game;
    }
    
}
