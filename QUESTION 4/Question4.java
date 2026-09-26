import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question4 {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("\"(?:\\\\.|[^\"])*\"|\\S+");

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        Map<String, ToDoubleFunction<String[]>> gradingRules = new HashMap<>();
        gradingRules.put("MCQ", values -> values[1].equalsIgnoreCase(values[2]) ? Double.parseDouble(values[3]) : 0.0);
        gradingRules.put("TF", values -> values[1].equalsIgnoreCase(values[2]) ? Double.parseDouble(values[3]) : 0.0);
        gradingRules.put("ESSAY", values -> essayScore(values[1], values[2], Double.parseDouble(values[3])));
        double total = 0.0;

        for (int index = 0; index < count; index++) {
            String[] tokens = tokenize(reader.readLine());
            String questionType = tokens[0].toUpperCase(Locale.ROOT);
            String[] values = {tokens[1], tokens[2], tokens[3], tokens[4]};
            double score = gradingRules.get(questionType).applyAsDouble(values);
            total += score;
            System.out.printf(Locale.US, "%s: %.2f%n", questionType, score);
        }

        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
    }

    private static double essayScore(String correctAnswer, String studentAnswer, double points) {
        String[] keywords = correctAnswer.split(",");
        int matches = 0;
        String normalizedAnswer = studentAnswer.toLowerCase(Locale.ROOT);
        for (String keyword : keywords) {
            String normalizedKeyword = keyword.trim().toLowerCase(Locale.ROOT);
            if (!normalizedKeyword.isEmpty() && normalizedAnswer.contains(normalizedKeyword)) {
                matches++;
            }
        }
        if (matches >= 2) {
            return points * 0.75;
        }
        if (matches == 1) {
            return points * 0.50;
        }
        return 0.0;
    }

    private static String[] tokenize(String line) {
        Matcher matcher = TOKEN_PATTERN.matcher(line);
        java.util.List<String> tokens = new java.util.ArrayList<>();
        while (matcher.find()) {
            String token = matcher.group();
            if (token.startsWith("\"") && token.endsWith("\"")) {
                token = token.substring(1, token.length() - 1);
            }
            tokens.add(token);
        }
        return tokens.toArray(new String[0]);
    }
}