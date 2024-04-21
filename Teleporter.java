 abstract class Teleporter {
    protected int head,tail;

    Boolean teleport(Player p){
        if(isHead(p.getPosition())) {
            p.setPosition(tail);
            return true;
        }else{
            return false;
        }
    }

    Boolean isHead(int pos){
        return pos==head;
    }

    int getHead(){
        return head;
    }

    int getTail(){
        return tail;
    }
}
