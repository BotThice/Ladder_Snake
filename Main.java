import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner playerInput = new Scanner(System.in);

        do{
            Game g = new Game();
            g.startGame();
        }while(playerInput.nextLine().equalsIgnoreCase("y"));
    }
}