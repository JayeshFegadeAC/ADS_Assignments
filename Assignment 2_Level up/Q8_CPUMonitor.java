import java.util.Arrays;

public class Q8_CPUMonitor {

    public static int[] maxSlidingWindow(int[] cpu, int k) {
        if (cpu == null || k <= 0 || cpu.length == 0) return new int[0];
        int n = cpu.length;
        int[] result = new int[n - k + 1];

        int[] deque = new int[n];
        int head = 0, tail = 0;

        for (int i = 0; i < n; i++) {
            if (head < tail && deque[head] < i - k + 1) {
                head++;
            }

            while (head < tail && cpu[deque[tail - 1]] <= cpu[i]) {
                tail--;
            }

            deque[tail++] = i;

            if (i >= k - 1) {
                result[i - k + 1] = cpu[deque[head]];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] cpu = {10, 20, 15, 30, 25, 5, 40};
        int k = 3;
        System.out.println("Max CPU Window: " + Arrays.toString(maxSlidingWindow(cpu, k)));
    }
}