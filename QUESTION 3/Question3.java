import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class Question3 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        Map<String, ToDoubleFunction<double[]>> feeRules = new HashMap<>();
        feeRules.put("STANDARD", values -> 5.0 + 0.50 * values[0] + 0.10 * values[1]);
        feeRules.put("EXPRESS", values -> 15.0 + values[0] + 0.20 * values[1]);
        feeRules.put("INTERNATIONAL", values -> 25.0 + 2.0 * values[0] + 0.50 * values[1] + values[2]);
        double total = 0.0;

        for (int index = 0; index < count; index++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String deliveryType = parts[0].toUpperCase(Locale.ROOT);
            double[] values = new double[3];
            values[0] = Double.parseDouble(parts[1]);
            values[1] = Double.parseDouble(parts[2]);
            if (parts.length > 3) {
                values[2] = Double.parseDouble(parts[3]);
            }
            double fee = feeRules.get(deliveryType).applyAsDouble(values);
            total += fee;
            System.out.printf(Locale.US, "%s: %.2f%n", deliveryType, fee);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}