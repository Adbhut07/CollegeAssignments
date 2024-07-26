import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalPens = 30;
        boolean isUserTurn;

        // Initialize the game
        System.out.println("Welcome to the Pen Game!");
        System.out.println("Who will start first? (1: User, 2: Computer)");
        int choice = scanner.nextInt();

        // Decide who starts
        isUserTurn = (choice == 1);

        // Game loop
        while (totalPens > 0) {
            if (isUserTurn) {
                System.out.println("It's your turn. Pick 1, 2, or 3 pens:");
                int userPick = scanner.nextInt();

                // Validate user input
                while (userPick < 1 || userPick > 3 || userPick > totalPens) {
                    System.out.println("Invalid pick. Please pick between 1 and 3 pens:");
                    userPick = scanner.nextInt();
                }

                totalPens -= userPick;
                System.out.println("You picked " + userPick + " pen(s). " + totalPens + " pen(s) remaining.");

            } else {
                // Computer's turn
                int computerPick = Math.min(random.nextInt(3) + 1, totalPens);
                totalPens -= computerPick;
                System.out.println("Computer picked " + computerPick + " pen(s). " + totalPens + " pen(s) remaining.");
            }

            // Check if the game has ended
            if (totalPens == 0) {
                if (isUserTurn) {
                    System.out.println("You picked the last pen. You win!");
                } else {
                    System.out.println("Computer picked the last pen. Computer wins!");
                }
                break;
            }

            // Switch turn
            isUserTurn = !isUserTurn;
        }

        scanner.close();
    }
}
