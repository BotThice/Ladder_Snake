import java.util.HashMap;

public class Player {
    HashMap<Integer,String> p;
    Integer position =1;

    Player(Integer order, String name){
        p = new HashMap<>();
        p.put(order,name);
    }

    protected void move(Integer step,Integer goal){
        position+=step;
        if(position>goal) {
            position = goal - position % goal;
        }
    }

    protected Integer getPosition(){
        return position;
    }

    protected void setPosition(Integer pos){
        position = pos;
    }
}
