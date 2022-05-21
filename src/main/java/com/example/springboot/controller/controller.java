package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

import javax.print.attribute.standard.Media;

import com.example.springboot.BO.Player;
import com.example.springboot.BO.requests.GenericRequest;
import com.example.springboot.BO.requests.PlayerRequest;
import com.example.springboot.BO.requests.ThrowRequest;
import com.example.springboot.BO.responses.GenericResponseObject;
import com.example.springboot.BO.responses.PlayerResponseObject;
import com.example.springboot.BO.responses.ScoreResponseObject;
import com.example.springboot.BO.responses.ScoreSheetResponseObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
public class controller {

    private HashMap<String, Player> playerCache = new HashMap<String, Player>();
    Gson gson = new Gson();
    private static final Logger log = LogManager.getLogger(controller.class);

    @GetMapping(path = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity healthcheck(){
        return ResponseEntity.ok(gson.toJson(new GenericResponseObject()));
    }
    
    @PostMapping(path ="/player",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPlayer(@RequestBody PlayerRequest playerRequest){
        GenericResponseObject responseObject;
        try{
            String id= generateNewPlayer(playerRequest.getName());
            responseObject = new PlayerResponseObject(id);
        }
        catch(Error error){
            log.error(error);
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
        }
        return ResponseEntity.ok(gson.toJson(responseObject));
        
    }

    @DeleteMapping(path ="/player",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removePlayer(@RequestBody GenericRequest genericRequest){
        GenericResponseObject responseObject; 
        try{
           playerCache.remove(genericRequest.getId());
           responseObject = new GenericResponseObject();
        }
        catch(Error error){
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
            
        }
        return ResponseEntity.ok(gson.toJson(responseObject));
    }

    @PostMapping(path ="/startGame",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startgame(@RequestBody GenericRequest genericRequest){
        GenericResponseObject responseObject; 
        try{
           playerCache.get(genericRequest.getId()).startGame();
           responseObject = new GenericResponseObject();
        }
        catch(Error error){
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
            
        }
        return ResponseEntity.ok(gson.toJson(responseObject));

    }
    @PostMapping(path ="/throwBall",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity throwBall(@RequestBody ThrowRequest throwRequest){
        GenericResponseObject responseObject; 
        try{
            Player player = playerCache.get(throwRequest.getId());
            player.getGame().throwBall(throwRequest.getFramdeIndex(), throwRequest.getBallIndex(), throwRequest.getScore());
            responseObject = new GenericResponseObject();
        }
        catch(Error error){
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
            
        }
        return ResponseEntity.ok(gson.toJson(responseObject));
    }
    @PostMapping(path ="/score",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getScore(@RequestBody GenericRequest genericRequest){
        GenericResponseObject responseObject; 
        try{
           int score = playerCache.get(genericRequest.getId()).getGame().getScore();
           responseObject = new ScoreResponseObject(score);
        }
        catch(Error error){
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
            
        }
        return ResponseEntity.ok(gson.toJson(responseObject));
    }
    @PostMapping(path ="/scoresheet",consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getScoreSheet(@RequestBody GenericRequest genericRequest){
        GenericResponseObject responseObject; 
        try{
           String score = playerCache.get(genericRequest.getId()).getGame().getScoreSheet();
           responseObject = new ScoreSheetResponseObject(score);
        }
        catch(Error error){
            responseObject = new GenericResponseObject();
            responseObject.setErrorStatus(error.toString());
            
        }
        return ResponseEntity.ok(gson.toJson(responseObject));
    }

    //create a new player
    private String generateNewPlayer(String name){
        String id = addNumberToId(0, name);
        playerCache.put(id, new Player());
        return id;
    }

    //generate a unique id by adding numbers to the players name until it is unique in the cache
    private String addNumberToId(int i,String id){
        String tempId = id;
        do{
            if(i != 0){
                tempId = id + i;
            }
            i++;
        }
        while(playerCache.get(tempId) != null);
        return tempId;
    }

}
