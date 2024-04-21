import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    @Test
    void add(){
        Leaderboard l = new Leaderboard();
        for(Integer i=0;i<10;i++){
            assertTrue(l.add(new Player(i,i.toString()+" Kub")));
        }
    }
}