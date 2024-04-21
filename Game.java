import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static int playerAmount,ladderAmount,snakeAmount,mapRow,mapCol,mapGoal;
    private static Queue<Player> order;
    private static ArrayList<Ladder> ladder;
    private static ArrayList<Snake> snake;
    private static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        playerAmount = 4;
        ladderAmount = 6;
        snakeAmount = 6;
        mapCol = 10;
        mapRow = 10;
        mapGoal = mapCol*mapRow;

        do{
            Game game = new Game();
            game.setupPlayer();
            game.setupLadder();
//            game.setupSnake();
//            game.start();

        }while(scanner.nextLine().equalsIgnoreCase("y"));

    }

    public void setupPlayer(){
        for(int i=1;i<5;i++){
            System.out.print("P"+i+" name's: ");
            String name = scanner.nextLine();
            order.add(new Player(i,name));
        }
    }

    public void setupLadder(){
        int minHeadPos =2, maxHead = mapGoal-1, maxTail = mapGoal, minTail;

        for(int i=0;i<ladderAmount;i++){
            int head = (int)(Math.random() * (maxHead - minHeadPos + 1)) + minHeadPos; // random 2 to 99
            minTail = head+1;
            int tail = (int)(Math.random() * (maxTail - minTail +1)) + minTail; // random head+1 to mapGoal
            ladder.add(new Ladder(head,tail));
        }


    }

    protected Integer rollDice(){
        Random dice = new Random();
        return dice.nextInt(6)+1;
    }
}
