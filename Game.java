import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private Integer diceFaces;
    private List<Player> playersTurnOrder;
    private List<Ladder> ladders;
    private List<Snake> snakes;
    private List<Integer> ladderOrSnakeHeadPosition;
    private Leaderboard leaderboard;
    private Board board;
    private final Scanner playerInput = new Scanner(System.in);

    public void startGame() throws InterruptedException {
        setupBoard();
        setupPlayer();
        setupTeleporter();
        setupLeaderboard();
        play();
        showLeaderboard();
    }

    public void setupBoard() {
        Integer boardColumn = 10;
        Integer boardRow = 10;
        board = new Board(boardRow, boardColumn);
    }

    private void setupTeleporter(){
        ladderOrSnakeHeadPosition = new LinkedList<>();
        setupLadder();
        setupSnake();
    }

    public void setupPlayer() {
        playersTurnOrder = new LinkedList<>();
        Integer playerAmount = 4;

        for(int codeNameRunner = 1; codeNameRunner < playerAmount + 1; codeNameRunner++){
            String codeName = "P" + codeNameRunner;
            System.out.print(codeName + " name's: ");
            String name = playerInput.nextLine();
            playersTurnOrder.add(new Player(name));
        }
    }

    public void setupSnake() {
        snakes = new LinkedList<>(); // TODO : ตอบให้ได้ว่าทำไมถึงเปลี่ยนจาก arraylist เป็นแบบนี้
        Integer snakeAmount = 6;

        Integer maxTailPosition;
        Integer minTailPosition = 1;

        Integer headPosition;
        Integer tailPosition;

        while(snakes.size() < snakeAmount){
            headPosition = randomHead(ladderOrSnakeHeadPosition);
            ladderOrSnakeHeadPosition.add(headPosition);

            maxTailPosition = headPosition - 1;
            tailPosition = randomInRange(minTailPosition,maxTailPosition);

            snakes.add(new Snake(headPosition,tailPosition));
        }
    }

    public void setupLadder() {
        ladders = new LinkedList<>();
        Integer ladderAmount = 6;

        Integer maxTailPosition = board.getGoalPosition();
        Integer minTailPosition;

        Integer headPosition;
        Integer tailPosition;

        while(ladders.size() < ladderAmount){
            headPosition = randomHead(ladderOrSnakeHeadPosition);
            ladderOrSnakeHeadPosition.add(headPosition);

            minTailPosition = headPosition + 1;
            tailPosition = randomInRange(minTailPosition,maxTailPosition);

            ladders.add(new Ladder(headPosition,tailPosition));
        }
    }

    private void setupLeaderboard() {
        leaderboard = new Leaderboard();
    }

    public void showLeaderboard() {
        System.out.println("------------------- Game has ended -------------------");
        leaderboard.showBoard();
        System.out.print("Play again ? (Y/N) : ");
    }

    public void play() throws InterruptedException {
        Integer currentPlayerIndex = 0;

        while(playersTurnOrder.size() > 1){
            board.showBoard();

            Player currentPlayer = playersTurnOrder.get(currentPlayerIndex);
            String playerName = currentPlayer.getName();

            System.out.print(playerName + "'s turn: press Enter to roll a dice. ");
          //  playerInput.nextLine();
            Integer step = rollDice();

            currentPlayer.move(step);
            System.out.println(playerName + " got " + step + ".");

            if(isOnTeleporterHead(currentPlayer)){
// TODO : fix teleport logic here
//            if(ladderOrSnakeHeadPosition.contains(p.getPosition())){
//            Boolean isTeleported=false;
//            for(Ladder l:ladders){
//                isTeleported = l.teleport(p);
//                if(isTeleported){
//                    System.out.println("Ladder !!");
//                    break;
//                }
//            }
//
//            if(!isTeleported){
//                for(Snake s:snakes){
//                    isTeleported = s.teleport(p);
//                    if(isTeleported){
//                        System.out.println("Snake eats "+playerName);
//                        break;
//                    }
//                }
//            }
            }

            if(isReachedGoal(currentPlayer)){
                System.out.println(playerName + " finished!!");
                playersTurnOrder.remove(currentPlayer);
                leaderboard.addFinishedPlayer(currentPlayer);
            }else{
                if(currentPlayer.getPosition() > board.getGoalPosition()){
                    Integer stepBack = (-2) * currentPlayer.getPosition() % board.getGoalPosition();
                    currentPlayer.move(stepBack);
                }

                System.out.println(playerName + "'s position: " + currentPlayer.getPosition());
                currentPlayerIndex++;
            }

            currentPlayerIndex = currentPlayerIndex % playersTurnOrder.size();

            TimeUnit.MILLISECONDS.sleep(250);
        }

        Player lastPlayer = playersTurnOrder.removeFirst();
        leaderboard.addFinishedPlayer(lastPlayer);
    }

    protected int randomHead(List<Integer> headPositionList) {
        Integer minHeadPosition = 2;
        Integer maxHeadPosition = board.getGoalPosition() - 1;
        Integer headPosition;
        do{
            headPosition = randomInRange(minHeadPosition,maxHeadPosition);
        }while(headPositionList.contains(headPosition));
        return headPosition;
    }

    protected Integer randomInRange(Integer min,Integer max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    protected Integer rollDice() {
        diceFaces = 6;
        return randomInRange(1,diceFaces);
    }

    public Boolean isOnTeleporterHead(Player p) {
        return ladderOrSnakeHeadPosition.contains(p.getPosition());
    }

    public Boolean isReachedGoal(Player p) {
        return p.getPosition().equals(board.getGoalPosition());
    }
}