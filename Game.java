import java.util.*;
import java.util.concurrent.TimeUnit;

// TODO : add space after , parameters
public class Game {
    // TODO : Create Class for a dice
    private Integer diceFaces;
    private List<Player> playersTurnOrder;
    private List<Ladder> ladders; // TODO : move this to Class Board
    private List<Integer> ladderOrSnakeHeadPosition; // TODO : setup ladder, snake without using this
    private Leaderboard leaderboard;
    private Board board;
    private final Scanner playerInput = new Scanner(System.in);

    public void startGame() throws InterruptedException {
        // TODO : switch setupPlayer and Board
        setupPlayer();
        setupBoard();
        setupTeleporter();
        setupLeaderboard();
        play();
        showLeaderboard();
    }

    private void setupBoard() {
        // TODO : ระวังตอน row หรือ column เป็น -
        Integer boardColumn = 10;
        Integer boardRow = 10;
        board = new Board(boardRow, boardColumn);
    }

    private void setupTeleporter() {
        ladderOrSnakeHeadPosition = new LinkedList<>();
        setupLadder();
        setupSnake();
    }

    private void setupPlayer() {
        playersTurnOrder = new LinkedList<>();
        Integer playerAmount = 4;

        for(int codeNameRunner = 1; codeNameRunner < playerAmount + 1; codeNameRunner++) {
            String codeName = "P" + codeNameRunner;
            System.out.print(codeName + " name's: ");
            String name = playerInput.nextLine();
            playersTurnOrder.add(new Player(name));
        }
    }

    private void setupSnake() {
        Integer snakeAmount = 6;

        Integer maxTailPosition;
        Integer minTailPosition = 1;

        Integer headPosition;
        Integer tailPosition;

        List<Snake> snakesInBoard = board.getSnakes();

        while(snakesInBoard.size() < snakeAmount) {
            headPosition = randomHeadPosition();// TODO : change this logic => create new list that contain unique Integer and pick one to initial head position
            ladderOrSnakeHeadPosition.add(headPosition);

            maxTailPosition = headPosition - 1;
            tailPosition = randomInRange(minTailPosition,maxTailPosition);

            snakesInBoard.add(new Snake(headPosition,tailPosition));
        }
    }

    private void setupLadder() {
        ladders = new LinkedList<>();
        Integer ladderAmount = 6;

        Integer maxTailPosition = board.getGoalPosition();
        Integer minTailPosition;

        Integer headPosition;
        Integer tailPosition;

        List<Ladder> laddersInBoard = board.getLadders();

        while(laddersInBoard.size() < ladderAmount) {
            headPosition = randomHeadPosition();
            ladderOrSnakeHeadPosition.add(headPosition);

            minTailPosition = headPosition + 1;
            tailPosition = randomInRange(minTailPosition,maxTailPosition);

            laddersInBoard.add(new Ladder(headPosition,tailPosition));
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

        while(playersTurnOrder.size() > 1) {
            board.showBoard();

            Player currentPlayer = playersTurnOrder.get(currentPlayerIndex);
            String playerName = currentPlayer.getName();

            System.out.print(playerName + "'s turn: press Enter to roll a dice. ");
          //  playerInput.nextLine();
            //TODO : face=> step
            Integer face = rollDice();

            currentPlayer.move(face);
            System.out.println(playerName + " got " + face + ".");

            // TODO : fix this
            if(isOnLadderHead(currentPlayer)) {
                laddersTeleport(currentPlayer);
            }else if(isOnSnakeHead(currentPlayer)) {
                snakesTeleport(currentPlayer);
            }

            if(isReachedGoal(currentPlayer)) {
                System.out.println(playerName + " finished!!");
                playersTurnOrder.remove(currentPlayer);
                leaderboard.addFinishedPlayer(currentPlayer);
                // TODO : space before else and after else
            }else{
                if(currentPlayer.getPosition() > board.getGoalPosition()) {
                    // TODO : change to easier calculation
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

    protected int randomHeadPosition() {
        Integer minHeadPosition = 2;
        Integer maxHeadPosition = board.getGoalPosition() - 1;
        return randomInRange(minHeadPosition,maxHeadPosition);
    }

    private Integer randomInRange(Integer min,Integer max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    protected Integer rollDice() {
        diceFaces = 6;
        return randomInRange(1,diceFaces);
    }

    private Boolean isOnLadderHead(Player target) {
        for(Ladder checkingLadder:ladders) {
            if(target.getPosition().equals(checkingLadder.getHead())) {
                return true;
            }
        }

        return false;
    }

    private void laddersTeleport(Player target) {
        for(Ladder finding:ladders) {
            if(target.getPosition().equals(finding.getHead())) {
                finding.teleport(target);
                break;
            }
        }
    }

    private Boolean isOnSnakeHead(Player target) {
        List<Snake> snakes = board.getSnakes();
        for(Snake checkingSnake:snakes) {
            if(target.getPosition().equals(checkingSnake.getHead())) {
                return true;
            }
        }

        return false;
    }

    private void snakesTeleport(Player target) {
        // TODO : change snakes => snakesInBoard
        List<Snake> snakes = board.getSnakes();
        // TODO : change finding => ?
        for(Snake finding:snakes) {
            if(target.getPosition().equals(finding.getHead())) {
                finding.teleport(target);
                break;
            }
        }
    }
    public Boolean isReachedGoal(Player currentPlayer) {
        return currentPlayer.getPosition().equals(board.getGoalPosition());
    }
}