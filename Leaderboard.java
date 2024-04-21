import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leaderboard {
    private LinkedList<Player> list;
    Leaderboard(){
        list = new LinkedList<>();
    }

    protected void showBoard(){
        int place =1;
        System.out.println("============ Leaderboard ============");
        while(!list.isEmpty()){
            Player p =list.poll();
            System.out.println(place+"."+p.getName());
            place++;
        }
        System.out.println("=====================================");
    }

    protected Boolean add(Player p){
        return list.add(p);
    }

}
