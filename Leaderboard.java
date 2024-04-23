import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leaderboard {
    private LinkedList<Player> playersFinishedOrder;
    Leaderboard(){
        playersFinishedOrder = new LinkedList<>();
    }

    protected void showBoard(){
        int place = 1;
        System.out.println("============= Leaderboard =============");

        while(!playersFinishedOrder.isEmpty()){
            Player p = playersFinishedOrder.poll();
            System.out.println(place + "." + p.getName());
            place++;
        }

        System.out.println("======================================");
    }

    protected void addFinishedPlayer(Player p){
        playersFinishedOrder.addLast(p);
    }

}
