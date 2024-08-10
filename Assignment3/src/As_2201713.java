import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class As_2201713 {
    public static void main(String[] args) {
        int classCount = 0;
        boolean inBlockComment = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip single-line comments
                if (line.startsWith("//")) continue;

                // Handle block comments
                if (line.startsWith("/*")) {
                    inBlockComment = true;
                }
                if (inBlockComment) {
                    if (line.endsWith("*/")) {
                        inBlockComment = false;
                    }
                    continue;
                }

                // Remove inline block comments
                line = line.replaceAll("/\\*.*?\\*/", "").trim();

                // Count actual class declarations
                if (line.matches(".*\\bclass\\b\\s+\\w+.*\\{")) {
                    classCount++;
                } else if (line.matches(".*\\bclass\\b\\s+\\w+.*") && reader.readLine().trim().equals("{")) {
                    classCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Number of classes: " + classCount);
    }
}
