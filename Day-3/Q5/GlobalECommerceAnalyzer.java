import java.util.Arrays;
import java.util.List;

// Transaction class
class Transaction {

    private String transactionId;
    private String status;
    private String category;
    private double amount;

    public Transaction(String transactionId,
                       String status,
                       String category,
                       double amount) {

        this.transactionId = transactionId;
        this.status = status;
        this.category = category;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

// Sales Analyzer class
class SalesAnalyzer {

    public double calculateElectronicsRevenue(
            List<Transaction> transactions) {

        // Sirf completed electronics transactions ka revenue
        return transactions.stream()

                .filter(transaction ->
                        "COMPLETED".equals(
                                transaction.getStatus()))

                .filter(transaction ->
                        "ELECTRONICS".equals(
                                transaction.getCategory()))

                .mapToDouble(
                        Transaction::getAmount)

                .sum();
    }
}

// Driver class
public class GlobalECommerceAnalyzer {

    public static void main(String[] args) {

        List<Transaction> transactionList =
                Arrays.asList(

                        new Transaction(
                                "TXN101",
                                "COMPLETED",
                                "ELECTRONICS",
                                49999.99
                        ),

                        new Transaction(
                                "TXN102",
                                "PENDING",
                                "ELECTRONICS",
                                12999.50
                        ),

                        new Transaction(
                                "TXN103",
                                "COMPLETED",
                                "FASHION",
                                2999.00
                        ),

                        new Transaction(
                                "TXN104",
                                "COMPLETED",
                                "ELECTRONICS",
                                15999.75
                        ),

                        new Transaction(
                                "TXN105",
                                "FAILED",
                                "ELECTRONICS",
                                8999.99
                        )
                );

        SalesAnalyzer analyzer =
                new SalesAnalyzer();

        double totalRevenue =
                analyzer.calculateElectronicsRevenue(
                        transactionList
                );

        System.out.println(
                "Electronics Revenue = ₹"
                        + totalRevenue
        );
    }
}