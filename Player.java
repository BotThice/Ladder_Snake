
public class Player {
    private final String codeName;
    private final String name;
    private Integer position = 1;

    Player(String codeName,String name){
       this.codeName = codeName;
       this.name = name;
    }

    protected void move(Integer step,Integer goal){
        position += step;
        if(position > goal){
            position = goal - position % goal;
        }
    }

    protected Integer getPosition(){
        return position;
    }

    protected String getCodeName(){
        return codeName;
    }

    protected String getName(){
        return name;
    }

    protected void setPosition(Integer pos){
        position = pos;
    }
}
