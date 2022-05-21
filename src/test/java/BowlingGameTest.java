import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.springboot.BO.BowlingGame;

import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @Test
    public void testScoring(){
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.throwBall(0, 0, 0);
        bowlingGame.throwBall(0, 1, 10); //10
        bowlingGame.throwBall(1, 0, 10); //30
        bowlingGame.throwBall(2, 0, 5); //40
        bowlingGame.throwBall(2,1,5); //50

        assertEquals(bowlingGame.getScoreSheet(),"-/X5/");
        assertEquals(bowlingGame.getScore(),50);
    }
    
}
