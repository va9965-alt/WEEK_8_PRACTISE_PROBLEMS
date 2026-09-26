import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class Question1 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine().trim());
        Map<String, DoubleUnaryOperator> feeRules = new HashMap<>();
        feeRules.put("CARD", amount -> amount * 1.02);
        feeRules.put("WALLET", amount -> amount * 1.01);
        feeRules.put("BANKTRANSFER", amount -> amount);
        double total = 0.0;

        for (int index = 0; index < count; index++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            String paymentType = parts[0].toUpperCase(Locale.ROOT);
            double amount = Double.parseDouble(parts[1]);
            double adjustedAmount = feeRules.get(paymentType).applyAsDouble(amount);
            total += adjustedAmount;
            System.out.printf(Locale.US, "%s: %.2f%n", paymentType, adjustedAmount);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}