
public class Player {
    private final String name;
    private Integer position = 1;

    Player(String name) {
       this.name = name;
    }

    protected void move(Integer step) {
        position += step;
    }

    protected Integer getPosition() {
        return position;
    }

    protected String getName() {
        return name;
    }

    protected void setPosition(Integer pos) {
        position = pos;
    }
}