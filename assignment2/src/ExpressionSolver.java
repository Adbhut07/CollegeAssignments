import java.util.Scanner;
import java.util.*;

public class ExpressionSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression:");
        String input = scanner.nextLine();
        scanner.close();

        // Remove all spaces from the input
        input = input.replaceAll("\\s", "");

        String operation = input.substring(0, input.indexOf('('));

        String numbers = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
        String[] operands = numbers.split(",");

        int x1 = Integer.parseInt(operands[0]);
        int x2 = Integer.parseInt(operands[1]);

        int result = 0;
        switch (operation) {
            case "ADD":
                result = x1 + x2;
                break;
            case "SUB":
                result = x1 - x2;
                break;
            case "MUL":
                result = x1 * x2;
                break;
            case "DIV":
                result = x1 / x2;
                break;
            case "POW":
                result = (int) Math.pow(x1, x2);
                break;
            default:
                System.out.println("Invalid operation.");
                return;
        }
        System.out.println(x1 + " " + operation + " " + x2 + " = " + result);
    }
}
