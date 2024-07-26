import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalPens = 30;
        boolean isUserTurn;

        System.out.println("Welcome to the Pen Game!");
        System.out.println("Who will start first? (1: User, 2: Computer)");
        int choice = scanner.nextInt();

        isUserTurn = (choice == 1);

        while (totalPens > 0) {
            if (isUserTurn) {
                System.out.println("It's your turn. Pick 1, 2, or 3 pens:");
                int userPick = scanner.nextInt();

                while (userPick < 1 || userPick > 3 || userPick > totalPens) {
                    System.out.println("Invalid pick. Please pick between 1 and 3 pens:");
                    userPick = scanner.nextInt();
                }

                totalPens -= userPick;
                System.out.println("You picked " + userPick + " pen(s). " + totalPens + " pen(s) remaining.");
            } else {
                int computerPick = determineComputerPick(totalPens);
                totalPens -= computerPick;
                System.out.println("Computer picked " + computerPick + " pen(s). " + totalPens + " pen(s) remaining.");
            }

            if (totalPens == 0) {
                if (isUserTurn) {
                    System.out.println("You picked the last pen. You win!");
                } else {
                    System.out.println("Computer picked the last pen. Computer wins!");
                }
                break;
            }

            isUserTurn = !isUserTurn;
        }

        scanner.close();
    }

    private static int determineComputerPick(int totalPens) {
        int pensToPick;

        if (totalPens % 4 == 0) {
            pensToPick = 1;
        } else {
            pensToPick = totalPens % 4;
        }

        return Math.min(pensToPick, Math.min(3, totalPens));
    }
}
