import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

public class Question2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        LocalDate currentDate = LocalDate.of(2023, 10, 26);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        Map<String, Function<LocalDate, LocalDate>> dueDateRules = new HashMap<>();
        dueDateRules.put("BOOK", date -> date.plusDays(14));
        dueDateRules.put("DVD", date -> date.plusDays(7));
        dueDateRules.put("MAGAZINE", date -> date.plusDays(3));

        for (int index = 0; index < count; index++) {
            String line = reader.readLine().trim();
            int separator = line.indexOf(' ');
            String itemType = line.substring(0, separator).toUpperCase(Locale.ROOT);
            String title = line.substring(separator + 1).trim();
            if (title.startsWith("\"") && title.endsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }
            LocalDate dueDate = dueDateRules.get(itemType).apply(currentDate);
            System.out.println(title + ": " + dueDate.format(formatter));
        }
    }
}