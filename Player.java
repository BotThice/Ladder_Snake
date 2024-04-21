import java.util.HashMap;

public class Player {
    Integer order;
    String name;
    Integer position =1;

    Player(Integer order, String name){
       this.order = order;
       this.name = name;
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

    protected Integer getOrder(){
        return order;
    }

    protected String getName(){
        return name;
    }

    protected void setPosition(Integer pos){
        position = pos;
    }
}
