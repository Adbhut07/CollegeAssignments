import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the name of the Java file as an argument.");
            return;
        }

        String fileName = args[0];  // The file name is passed as the first argument

        try {
            int classCount = countClasses(fileName);
            System.out.println("Number of classes found: " + classCount);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static int countClasses(String fileName) throws IOException {
        boolean inComment = false;
        boolean inString = false;
        int classCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int i = 0;
                while (i < line.length()) {
                    if (!inComment && !inString) {
                        if (line.startsWith("//", i)) {
                            break;
                        }
                        
                        if (line.startsWith("/*", i)) {
                            inComment = true;
                            i += 2;
                            continue;
                        }

                        // Check for a string literal
                        if (line.startsWith("\"", i)) {
                            inString = true;
                            i += 1;
                            continue;
                        }

                        // Check for the "class" keyword
                        if (line.startsWith("class", i) &&
                                (i + 5 == line.length() || !Character.isLetterOrDigit(line.charAt(i + 5)))) {
                            classCount++;
                            i += 5;
                            continue;
                        }
                    } else if (inComment) {
                        // Check for the end of multi-line comment
                        if (line.startsWith("*/", i)) {
                            inComment = false;
                            i += 2;
                            continue;
                        }
                    } else if (inString) {
                        // Check for the end of string literal
                        if (line.startsWith("\"", i)) {
                            inString = false;
                            i += 1;
                            continue;
                        }
                    }
                    i++;
                }
            }
        }

        return classCount;
    }
}
