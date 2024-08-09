import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountClass {

    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Function to count the number of classes, excluding classes inside comments and strings
    private static int countClasses(String content) {
        int count = 0;
        boolean inComment = false;
        boolean inString = false;

        Pattern pattern = Pattern.compile("\\bclass\\b");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Check if 'class' is inside a comment or string
            if (!isInsideCommentOrString(content, start, end, inComment, inString)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isInsideCommentOrString(String content, int start, int end, boolean inComment, boolean inString) {
        for (int i = 0; i < start; i++) {
            char c = content.charAt(i);
            if (c == '/' && i + 1 < content.length()) {
                if (content.startsWith("//", i)) {
                    // Single-line comment
                    break;
                } else if (content.startsWith("/*", i)) {
                    // Start of multi-line comment
                    inComment = true;
                } else if (content.startsWith("*/", i)) {
                    // End of multi-line comment
                    inComment = false;
                }
            } else if (c == '\"') {
                // Toggle string literal state
                inString = !inString;
            }
        }
        return inComment || inString;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CountClass <source-file>");
            return;
        }

        String fileName = args[0];
        System.out.println("Reading file: " + fileName);
        try {
            String fileContent = readFile(fileName);

            int classCount = countClasses(fileContent);
            System.out.println("Number of classes: " + classCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
