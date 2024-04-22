import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LadderSnakeTest {

    @Test
    void LadderWarp(){
        Ladder l1 = new Ladder(5,10), l2 = new Ladder(11,20);
        Player p = new Player(1,"111");

        p.move(4,100);
        l1.teleport(p);
        assertEquals(10,p.getPosition());

        p.move(1,100);
        l2.teleport(p);
        assertEquals(20,p.getPosition());

        l1.teleport(p);
        assertEquals(20,p.getPosition());

        l2.teleport(p);
        assertEquals(20,p.getPosition());
    }

    @Test
    void SnakeWarp(){
        Snake s1 = new Snake(20,1), s2 = new Snake(10,5);
        Player p = new Player(1,"A");

        p.move(9,100);
        s2.teleport(p);
        assertEquals(5,p.getPosition());

        p.move(15,100);
        s1.teleport(p);
        assertEquals(1,p.getPosition());


        p.move(1,100);
        s1.teleport(p);
        assertEquals(2,p.getPosition());

        s2.teleport(p);
        assertEquals(2,p.getPosition());
    }
}