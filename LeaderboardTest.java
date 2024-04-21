import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    @Test
    Leaderboard add(){
        Leaderboard l = new Leaderboard();
        for(Integer i=0;i<10;i++){
            assertTrue(l.add(new Player(i,i.toString()+" Kub")));
        }
        return l;
    }

    @Test
    void show(){
        Leaderboard l = add();
        l.showBoard();
    }
}