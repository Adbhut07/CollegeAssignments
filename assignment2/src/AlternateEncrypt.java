import java.util.Scanner;

public class AlternateEncrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to encrypt:");
        String input = scanner.nextLine();
        scanner.close();

        char[] encrypted = new char[input.length()];

        int start = 0;
        int end = input.length() - 1;
        int index = 0;

        while (start <= end) {
            if (index % 2 == 0) {
                encrypted[index] = input.charAt(start);
                start++;
            } else {
                encrypted[index] = input.charAt(end);
                end--;
            }
            index++;
        }

        String encryptedString = new String(encrypted);

        System.out.println("Encrypted string: " + encryptedString);
    }
}
