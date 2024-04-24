 abstract class Teleporter {
    protected Integer head;
    protected Integer tail;

    void teleport(Player p) {
        if(isHead(p.getPosition())) {
            p.setPosition(tail);
        }
    }

    Boolean isHead(Integer position) {
        return position.equals(head);
    }

    Integer getHead() {
        return head;
    }

    Integer getTail() {
        return tail;
    }
}