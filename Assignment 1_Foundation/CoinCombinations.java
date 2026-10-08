public class CoinCombinations {

    public static int countWays(int[] coins, int n, int amount) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || n <= 0) {
            return 0;
        }
        
        return countWays(coins, n, amount - coins[n - 1]) + countWays(coins, n - 1, amount);
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;

        int ways = countWays(coins, coins.length, amount);
        System.out.println("Number of ways to make Rs. " + amount + " is: " + ways);
    }
}