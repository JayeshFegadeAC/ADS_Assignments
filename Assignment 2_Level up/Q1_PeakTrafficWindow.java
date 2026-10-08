public class Q1_PeakTrafficWindow {

    public static void peakWindowSliding(long[] req, int k) {
        if (req == null || req.length == 0 || k <= 0 || k > req.length) {
            System.out.println("Invalid input or window size");
            return;
        }

        long currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += req[i];
        }

        long maxSum = currentSum;
        int bestStart = 0;

        for (int i = 1; i <= req.length - k; i++) {
            currentSum = currentSum - req[i - 1] + req[i + k - 1];
            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = i;
            }
        }

        System.out.println("Peak " + k + "-minute load " + maxSum + 
                           " requests (minutes " + (bestStart + 1) + " to " + (bestStart + k) + ")");
    }

    public static void runBenchmark(int n, int k) {
        long[] req = new long[n];
        for (int i = 0; i < n; i++) req[i] = i % 500;

        long bruteOps = 0;
        long bestBrute = 0;
        for (int i = 0; i + k <= n; i++) {
            long sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += req[j];
                bruteOps++;
            }
            bestBrute = Math.max(bestBrute, sum);
        }

        long slidingOps = 0;
        long currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += req[i];
            slidingOps++;
        }
        long bestSliding = currentSum;
        for (int i = 1; i <= n - k; i++) {
            currentSum = currentSum - req[i - 1] + req[i + k - 1];
            slidingOps += 2;
            bestSliding = Math.max(bestSliding, currentSum);
        }

        System.out.println("N=" + n + ", K=" + k + " | Brute Ops: " + bruteOps + " | Sliding Ops: " + slidingOps);
    }

    public static void main(String[] args) {
        long[] req = {120, 340, 560, 210, 90, 680, 720, 150};
        int k = 3;
        peakWindowSliding(req, k);

        System.out.println("\n--- Benchmarks ---");
        runBenchmark(1000, 1000);
        runBenchmark(100000, 1000);
        runBenchmark(1000000, 1000);
    }
}