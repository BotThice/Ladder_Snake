 abstract class Teleporter {
    protected Integer head;
    protected Integer tail;

    Boolean teleport(Player p){
        if(isHead(p.getPosition())) {
            p.setPosition(tail);
            return true;
        }else{
            return false;
        }
    }

    Boolean isHead(Integer position){
        return position.equals(head);
    }

    int getHead(){
        return head;
    }

    int getTail(){
        return tail;
    }
}
