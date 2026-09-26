import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class Question5 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        Map<String, ToDoubleFunction<double[]>> fareRules = new HashMap<>();
        fareRules.put("BUS", values -> Math.min(10.0, 2.0 + 0.10 * values[0]));
        fareRules.put("TRAIN", values -> 3.0 + 0.15 * values[0]);
        fareRules.put("METRO", values -> (1.50 + 0.20 * values[0]) * values[1]);
        double total = 0.0;

        for (int index = 0; index < count; index++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String transportType = parts[0].toUpperCase(Locale.ROOT);
            double[] values = new double[2];
            values[0] = Double.parseDouble(parts[1]);
            values[1] = parts.length > 2 ? Double.parseDouble(parts[2]) : 1.0;
            double fare = fareRules.get(transportType).applyAsDouble(values);
            total += fare;
            System.out.printf(Locale.US, "%s: %.2f%n", transportType, fare);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}