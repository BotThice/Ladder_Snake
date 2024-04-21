import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void moveMethod(){
        Player p1 = new Player(1,"1");
        //Normal Case
        p1.move(50,100);
        assertEquals(51,p1.getPosition());

        //เดินเกินเส้นชัย
        p1.setPosition(1);
        p1.move(10,10);
        assertEquals(9,p1.getPosition());
    }

}