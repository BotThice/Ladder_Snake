import java.util.*;

public class Game {
    private int playerAmount,ladderAmount,snakeAmount,mapRow,mapCol,mapGoal;
    private List<Player> order;
    private ArrayList<Ladder> ladder;
    private ArrayList<Snake> snake;
    private ArrayList<Integer> headPos;
    private Leaderboard leaderboard;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game g = new Game();
        g.varSetup();
        g.play();
    }
    public void varSetup(){
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
        leaderboard = new Leaderboard();
    }
    public void play() {

        do{
            setupPlayer();
            setupLadder();
            setupSnake();
            startGame();
            end();
        }while(scanner.nextLine().equalsIgnoreCase("y"));

    }

    public void end(){
        System.out.println("------------------- Game has ended -------------------");
        leaderboard.showBoard();
        System.out.print("Play again ? (Y/N) : ");
    }
    public  void startGame(){
        while(order.size() != 1){
            Player p = order.removeFirst();
                String text = "P"+p.getOrder();
                System.out.print(text+"'s turn: press Enter to roll a dice");
                scanner.nextLine();

                Integer step = rollDice();
                System.out.println(text+" got "+step+".");
                stepCal(p,step,text);

                if(p.getPosition().equals(mapGoal)){
                    System.out.println(text+" finished!!");
                    order.remove(p);
                    leaderboard.add(p);
                }else{
                    order.addLast(p);
                }
                // render map here
        }
        leaderboard.add(order.remove(0));

    }

    public void stepCal(Player p,int step, String text){
        p.move(step,mapGoal);
        if(headPos.contains(p.getPosition())){
            Boolean isTeleported=false;
            for(Ladder l:ladder){
                isTeleported = l.teleport(p);
                if(isTeleported){
                    System.out.println("Ladder !!");
                    break;
                }
            }
            if(!isTeleported){
                for(Snake s:snake){
                    isTeleported = s.teleport(p);
                    if(isTeleported){
                        System.out.println("Snake eats "+text);
                        break;
                    }
                }
            }
        }
        System.out.println(text+"'s position: "+p.getPosition());
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

    protected void setHeadPos(ArrayList<Integer> list){
        headPos = list;
    }
}
