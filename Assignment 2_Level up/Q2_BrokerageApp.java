public class Q2_BrokerageApp {

    static class Trade {
        int buyDay;
        int sellDay;
        int profit;

        Trade(int buyDay, int sellDay, int profit) {
            this.buyDay = buyDay;
            this.sellDay = sellDay;
            this.profit = profit;
        }

        @Override
        public String toString() {
            if (profit <= 0) return "No profitable trade this month (profit = 0)";
            return "Buy on day " + buyDay + " at price, sell on day " + sellDay + ", profit = " + profit;
        }
    }

    public static Trade maxProfitBruteForce(int[] prices) {
        int maxProfit = 0, buy = 0, sell = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int p = prices[j] - prices[i];
                if (p > maxProfit) {
                    maxProfit = p;
                    buy = i + 1;
                    sell = j + 1;
                }
            }
        }
        return new Trade(buy, sell, maxProfit);
    }

    public static Trade maxProfitSinglePass(int[] prices) {
        if (prices == null || prices.length < 2) return new Trade(0, 0, 0);

        int minPrice = prices[0];
        int minDay = 1;
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentDay = i + 1;
            int currentProfit = prices[i] - minPrice;

            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                buyDay = minDay;
                sellDay = currentDay;
            }

            if (prices[i] < minPrice) {
                minPrice = prices[i];
                minDay = currentDay;
            }
        }

        return new Trade(buyDay, sellDay, maxProfit);
    }

    public static int maxProfitMultipleTrades(int[] prices) {
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        int[] p1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Single Trade: " + maxProfitSinglePass(p1));
        System.out.println("Multiple Trades Profit: " + maxProfitMultipleTrades(p1));

        int[] p2 = {9, 7, 4, 3, 1};
        System.out.println("Single Trade (Falling): " + maxProfitSinglePass(p2));
    }
}