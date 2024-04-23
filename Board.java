import java.util.List;

public class Board {
    private final Integer maxRow;
    private final Integer maxCol;
    private final Integer goal;
    private List<Ladder> ladders;
    private List<Snake> snakes;

    Board(Integer maxRow,Integer maxCol){
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        goal = maxCol * maxRow;
    }

    protected void renderMap(){
        Integer runningNumber = goal;
        System.out.println(String.format("%" + 10 * maxCol + "s"," ").replace(" ","-"));

        for(int row = maxRow; row > 0; row--){
            for(int col = 1; col <= maxCol; col++){
                if(isEvenRow(row)){
                    System.out.printf("| %-8d",runningNumber);
                    runningNumber--;
                }else{
                    runningNumber++;
                    System.out.printf("| %-8d",runningNumber);
                }
            }

            runningNumber -= 10;
            System.out.println();
        }

        System.out.println(String.format("%" + 10 * maxCol + "s"," ").replace(" ","-"));
    }

    protected void setupBoard(){

    }

    private boolean isEvenRow(Integer row){
        return row % 2 == 0;
    }

}
