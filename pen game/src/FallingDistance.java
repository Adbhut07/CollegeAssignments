import java.util.Scanner;

public class FallingDistance {
    public static void main(String[] args) {

        final double GRAVITY = 9.81;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the time (in seconds): ");
        int time = scanner.nextInt();

        double distance = 0.5 * GRAVITY * time * time;

        System.out.printf("The object will fall %.2f meters in %d seconds.\n", distance, time);

        scanner.close();
    }
}
