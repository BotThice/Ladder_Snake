import java.util.List;

public class Board {
    private final Integer maxRow;
    private final Integer maxCol;
    private final Integer goalPosition;
    private List<Ladder> ladders;
    private List<Snake> snakes;

    Board(Integer maxRow,Integer maxCol) {
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        goalPosition = maxCol * maxRow;
    }

    protected void showBoard() {
        Integer runningNumber = goalPosition;
        showBorder();
        
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

        showBorder();
    }
    
    private void showBorder(){
        System.out.println(String.format("%" + 10 * maxCol + "s"," ").replace(" ","-"));
    }

    protected void setupBoard() {

    }

    private boolean isEvenRow(Integer row) {
        return row % 2 == 0;
    }

    protected Integer getGoalPosition(){
        return goalPosition;
    }

}