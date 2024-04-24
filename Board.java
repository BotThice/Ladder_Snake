import java.util.List;

public class Board {
    private final Integer maxRow;
    private final Integer maxColumn;
    private final Integer goalPosition;
    private List<Ladder> ladders;
    private List<Snake> snakes;

    Board(Integer maxRow,Integer maxColumn) {
        this.maxColumn = maxColumn;
        this.maxRow = maxRow;
        goalPosition = maxColumn * maxRow;
    }

    protected void showBoard() {
        Integer runningNumber = goalPosition;
        showBorder();
        
        for(int row = maxRow; row > 0; row--) {
            for(int column = 1; column <= maxColumn; column++) {
                if(isEven(row)) {
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
    
    private void showBorder() {
        System.out.println(String.format("%" + 10 * maxColumn + "s"," ").replace(" ","-"));
    }

    protected void setupBoard() {

    }

    private boolean isEven(Integer number) {
        return number % 2 == 0;
    }

    protected Integer getGoalPosition() {
        return goalPosition;
    }

    protected List<Snake> getSnakes(){
        return snakes;
    }

    protected List<Ladder> getLadders(){
        return ladders;
    }
}