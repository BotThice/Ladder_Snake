import java.util.*;

public class Game {
    private static int playerAmount,ladderAmount,snakeAmount,mapRow,mapCol,mapGoal;
    private static List<Player> order;
    private static ArrayList<Ladder> ladder;
    private static ArrayList<Snake> snake;
    private  static ArrayList<Integer> headPos;
    private  static Queue<Player> leaderboard;
    private static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        playerAmount = 4;
        ladderAmount = 6;
        snakeAmount = 6;
        mapCol = 10;
        mapRow = 10;
        mapGoal = mapCol*mapRow;
        order = new LinkedList<>();
        snake = new ArrayList<>();
        ladder = new ArrayList<>();
        headPos  = new ArrayList<>();
        leaderboard = new PriorityQueue<>();

        do{
            Game game = new Game();
            game.setupPlayer();
            game.setupLadder();
            game.setupSnake();
            game.startGame();
            game.end();
        }while(scanner.nextLine().equalsIgnoreCase("y"));

    }

    public void end(){
        System.out.println("------------------- Game has ended -------------------");

    }
    public  void startGame(){
        while(order.size() != 1){
            for(Player p:order){
                String text = "P"+p.getOrder();
                System.out.print(text+"'s turn: press Enter to roll a dice");
                scanner.nextLine();
                Integer step = rollDice();
                System.out.println(text+" got "+step+".");
                p.move(step,mapGoal);

                if(headPos.contains(p.getPosition())){
                    Boolean isTeleported=false;
                    for(Ladder l:ladder){
                        isTeleported = l.teleport(p);
                        if(isTeleported){break;}
                    }
                    if(!isTeleported){
                        for(Snake s:snake){
                            isTeleported = s.teleport(p);
                            if(isTeleported) break;
                        }
                    }
                }

                if(p.getPosition().equals(mapGoal)){
                    System.out.println(text+" finished!!");
                    order.remove(p);
                    leaderboard.add(p);
                }
            }
        }
        leaderboard.add(order.remove(0));

    }

    public void setupPlayer(){
        for(int i=1;i<playerAmount+1;i++){
            System.out.print("P"+i+" name's: ");
            String name = scanner.nextLine();
            order.add(new Player(i,name));
        }
    }

    public void setupLadder(){
       Integer maxTail = mapGoal, minTail;
       Integer head;

        for(int i=0;i<ladderAmount;i++){
            head = randomHead(headPos);// random 2 to 99
            headPos.add(head);
            minTail = head+1;

            int tail = (int)(Math.random() * (maxTail - minTail +1)) + minTail; // random head+1 to mapGoal
            ladder.add(new Ladder(head,tail));
        }


    }

    public void setupSnake(){
        Integer maxTail, minTail=1;
        Integer head;

        for(int i=0;i<snakeAmount;i++){
            head = randomHead(headPos);
            headPos.add(head);
            maxTail = head+1;

            int tail = (int)(Math.random() * (maxTail - minTail +1)) + minTail; // random 1 to head-1
            snake.add(new Snake(head,tail));
        }
    }

    protected int randomHead(ArrayList<Integer> headList){
        int minHeadPos =2, maxHead = mapGoal-1;
        Integer head;
        do{
            head = (int)(Math.random() * (maxHead - minHeadPos + 1)) + minHeadPos;
        }while(headList.contains(head));
        return head;
    }

    protected Integer rollDice(){
        Random dice = new Random();
        return dice.nextInt(6)+1;
    }
}
