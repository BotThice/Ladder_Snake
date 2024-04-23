import java.util.*;

public class Game {
    private Integer playerAmount,ladderAmount,snakeAmount,mapRow,mapCol,goal;
    private List<Player> playersTurnOrder;
    private List<Ladder> ladders;
    private List<Snake> snakes;
    private List<Integer> headPos;
    private Leaderboard leaderboard;
    private Board map;
    private final Scanner playerInput = new Scanner(System.in);

    public void startGame() {
        setupVariable();
        setupPlayer();
        setupLadder();
        setupSnake();
        play();
        showLeaderboard();
    }
    public void setupVariable(){
        System.out.print("Player amount : ");
        playerAmount = playerInput.nextInt();
        playerInput.nextLine();
        ladderAmount = 6;
        snakeAmount = 6;
        mapCol = 10;
        mapRow = 10;
        goal = mapCol * mapRow;
        playersTurnOrder = new LinkedList<>();
        snakes = new LinkedList<>();
        ladders = new LinkedList<>();
        headPos  = new LinkedList<>();
        leaderboard = new Leaderboard();
        map = new Board();
    }

    public void setupPlayer(){
        for(int codeNameRunner = 1; codeNameRunner < playerAmount + 1; codeNameRunner++){
            String codeName = "P" + codeNameRunner;
            System.out.print(codeName + " name's: ");
            String name = playerInput.nextLine();
            playersTurnOrder.add(new Player(codeName,name));
        }
    }

    public void setupSnake(){
        Integer maxTailPosition;
        Integer minTailPosition = 1;
        Integer head;

        for(int i = 0; i < snakeAmount; i++){
            head = randomHead(headPos);
            headPos.add(head);
            maxTailPosition = head - 1;

            int tail = randomFromInterval(minTailPosition,maxTailPosition);

            snakes.add(new Snake(head,tail));
        }
    }

    public void setupLadder(){
        Integer maxTailPostion = goal;
        Integer minTailPostion;
        Integer headPosition;

        for(int i = 0; i < ladderAmount; i++){
            headPosition = randomHead(headPos);
            headPos.add(headPosition);

            minTailPostion = headPosition + 1;
            int tailPosition = randomFromInterval(minTailPostion,maxTailPostion);

            ladders.add(new Ladder(headPosition,tailPosition));
        }


    }

    public void showLeaderboard(){
        System.out.println("------------------- Game has ended -------------------");
        leaderboard.showBoard();
        System.out.print("Play again ? (Y/N) : ");
    }

    public void play(){
        boolean isSkip = false;
        while(playersTurnOrder.size() != 1){
            map.renderMap();
            Player currentPlayer = playersTurnOrder.removeFirst();
            String codeName = currentPlayer.getCodeName();

            if(!isSkip){
                System.out.print(codeName + "'s turn: press Enter to roll a dice. ");
                String forSkip = playerInput.nextLine();

                if(forSkip.equalsIgnoreCase("skip")){
                    isSkip = true;
                }
            }

            Integer step = rollDice();
            currentPlayer.move(step,goal);
            System.out.println(codeName + " got " + step + ".");

            if(isOnTeleporterHead(currentPlayer)){
                // TODO : fix teleport logic her
//            if(headPos.contains(p.getPosition())){
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
//                        System.out.println("Snake eats "+codeName);
//                        break;
//                    }
//                }
//            }
            }

            System.out.println(codeName + "'s position: " + currentPlayer.getPosition());

            if(isReachedGoal(currentPlayer)){
                System.out.println(codeName + " finished!!");
                playersTurnOrder.remove(currentPlayer);
                leaderboard.addFinishedPlayer(currentPlayer);
            }else{
                playersTurnOrder.addLast(currentPlayer);
            }
        }

        Player lastPlayer = playersTurnOrder.removeFirst();
        leaderboard.addFinishedPlayer(lastPlayer);
    }

    protected int randomHead(List<Integer> headList){
        int minHeadPosition = 2;
        int maxHeadPosition = goal - 1;
        Integer head;
        do{
            head = randomFromInterval(minHeadPosition,maxHeadPosition);
        }while(headList.contains(head));
        return head;
    }

    protected Integer randomFromInterval(Integer min,Integer max){
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    protected Integer rollDice(){
        return randomFromInterval(1,6);
    }

    public Boolean isOnTeleporterHead(Player p){
        return headPos.contains(p.getPosition());
    }

    public Boolean isReachedGoal(Player p){
        return p.getPosition().equals(goal);
    }
}
