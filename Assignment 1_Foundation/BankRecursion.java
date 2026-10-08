public class BankRecursion {


    public static double calculateBalance(double amount, double rate, int years) {
        if (years == 0) {
            return amount;
        }
        double newAmount = amount + (amount * rate / 100);
        return calculateBalance(newAmount, rate, years - 1);
    }

    public static void main(String[] args) {
        double principal = 10000;
        double interestRate = 5; // 5%
        int years = 3;

        double finalBalance = calculateBalance(principal, interestRate, years);

        System.out.println("Initial Amount: Rs. " + principal);
        System.out.println("Balance after " + years + " years: Rs. " + finalBalance);
    }
}