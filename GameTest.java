import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void rollDice(){
        Game g = new Game();
        for(int i=0;i<1000;i++){
            Integer point = g.rollDice();
            assertTrue(point<7 && point>0 );
        }
    }

    @Test
    void ladder(){
        int mapGoal=100;
        int minHeadPos =2, maxHead = mapGoal-1, maxTail = mapGoal, minTail;
        int ladderAmount =1000;
        ArrayList<Ladder> ladder = new ArrayList<>();

        for(int i=0;i<ladderAmount;i++){
            int head = (int)(Math.random() * (maxHead - minHeadPos + 1)) + minHeadPos; // random 2 to 99
            minTail = head+1;
            int tail = (int)(Math.random() * (maxTail - minTail +1)) + minTail; // random head+1 to mapGoal
            ladder.add(new Ladder(head,tail));
        }

        for(Ladder l:ladder){

            assertTrue(l.getHead() >= minHeadPos && l.getHead() <= maxHead);

            assertTrue(l.getTail() >= l.getHead()+1 && l.getTail()<=mapGoal);
        }
    }

}