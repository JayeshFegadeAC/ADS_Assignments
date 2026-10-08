import java.util.HashMap;
import java.util.Map;

public class Q4_RateLimiter {
    private final int k;
    private final long windowSec;
    private final long[] ringBuffer;
    private int head;
    private int count;

    public Q4_RateLimiter(int k, long windowSec) {
        this.k = k;
        this.windowSec = windowSec;
        this.ringBuffer = new long[k];
        this.head = 0;
        this.count = 0;
    }

    public synchronized boolean allow(long timestampSec) {
        if (count == k) {
            long oldest = ringBuffer[head];
            if (timestampSec - oldest < windowSec) {
                return false;
            }
            head = (head + 1) % k;
            count--;
        }

        int tail = (head + count) % k;
        ringBuffer[tail] = timestampSec;
        count++;
        return true;
    }

    public static void main(String[] args) {
        Q4_RateLimiter limiter = new Q4_RateLimiter(3, 10);
        long[] times = {1, 2, 3, 4, 11, 12, 13, 14};

        System.out.print("Results: ");
        for (long t : times) {
            boolean allowed = limiter.allow(t);
            System.out.print((allowed ? "OK" : "429") + " ");
        }
        System.out.println();

        Map<String, Q4_RateLimiter> userLimiters = new HashMap<>();
        userLimiters.put("user_1", new Q4_RateLimiter(100, 60));
        System.out.println("Multi-user Rate Limiter initialized.");
    }
}