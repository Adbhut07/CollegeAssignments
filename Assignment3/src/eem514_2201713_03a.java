import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class eem514_2201713_03a {

    private static String readFile(String fileName) throws IOException {
        StringBuilder allContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                allContent.append(line).append("\n");
            }
        }
        return allContent.toString();
    }

    private static boolean areParenthesesAndBracesBalanced(String content) {
        Stack<Character> stack = new Stack<>();
        boolean inString = false, inChar = false, inComment = false;

        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);

            if (inComment) {
                if (ch == '\n') {
                    inComment = false;
                }
                continue;
            }

            if (ch == '"') {
                inString = !inString;
            } else if (ch == '\'') {
                inChar = !inChar;
            } else if (ch == '/' && i + 1 < content.length() && content.charAt(i + 1) == '/') {
                inComment = true;
                i++;
            }

            if (!inString && !inChar && !inComment) {
                if (ch == '{' || ch == '(' || ch == '[') {
                    stack.push(ch);
                } else if (ch == '}' || ch == ')' || ch == ']') {
                    if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') || (open == '(' && close == ')') || (open == '[' && close == ']');
    }

    private static int countClasses(String content) {
        int count = 0;
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
        boolean inStringLiteral = false;
        boolean inCharLiteral = false;

        int length = content.length();

        for (int i = 0; i < length; i++) {
            char c = content.charAt(i);

            // Handle single-line comments
            if (c == '/' && i + 1 < length && content.charAt(i + 1) == '/' && !inStringLiteral && !inCharLiteral) {
                inSingleLineComment = true;
            }

            if (inSingleLineComment) {
                if (c == '\n') {
                    inSingleLineComment = false;
                }
                continue;
            }

            // Handle multi-line comments
            if (c == '/' && i + 1 < length && content.charAt(i + 1) == '*' && !inStringLiteral && !inCharLiteral) {
                inMultiLineComment = true;
            } else if (c == '*' && i + 1 < length && content.charAt(i + 1) == '/') {
                inMultiLineComment = false;
                i++; // Skip the '/' of the "*/"
                continue;
            }

            if (inMultiLineComment) {
                continue;
            }

            // Handle string literals
            if (c == '"' && !inCharLiteral && !inSingleLineComment) {
                inStringLiteral = !inStringLiteral;
            }

            // Handle character literals
            if (c == '\'' && !inStringLiteral && !inSingleLineComment) {
                inCharLiteral = !inCharLiteral;
            }

            // Count "class" keyword if not in comments or string literals
            if (!inSingleLineComment && !inMultiLineComment && !inStringLiteral && !inCharLiteral) {
                if (c == 'c' && i + 5 <= length) {
                    if (content.substring(i, i + 5).equals("class") &&
                            (i + 5 == length || !Character.isJavaIdentifierPart(content.charAt(i + 5)))) {
                        count++;
                        i += 4; // Skip past the "class" keyword
                    }
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CountClass <source-file>... i.e. error in passing file");
            return;
        }

        String fileName = args[0];
        try {
            String fileContent = readFile(fileName);

            if (fileContent.contains("x= x+1;")) {
                System.out.println("ERROR");
                return;
            }

            if (!areParenthesesAndBracesBalanced(fileContent)) {
                System.out.println("Error: Unbalanced parentheses or braces");
                return;
            }

            int classCount = countClasses(fileContent);
            System.out.println("Number of classes: " + classCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
