import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RollNumberSearch {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RollNumberSearch <input_file>");
            return;
        }

        String filename = args[0];

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int N = scanner.nextInt();

            int[] rollNumbers = new int[N];
            for (int i = 0; i < N; i++) {
                rollNumbers[i] = scanner.nextInt();
            }

            int numberToSearchFor = scanner.nextInt();
            scanner.close();

            int result = binarySearchWithComparisonCount(rollNumbers, numberToSearchFor);
            System.out.println(result);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static int binarySearchWithComparisonCount(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (array[mid] == key) {
                return comparisons;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
