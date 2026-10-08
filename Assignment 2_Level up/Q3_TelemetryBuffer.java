import java.util.Random;

public class Q3_TelemetryBuffer {
    private double[] data;
    private int size;
    private long totalCopies;

    public Q3_TelemetryBuffer() {
        this.data = new double[4];
        this.size = 0;
        this.totalCopies = 0;
    }

    public void append(double r) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = r;
    }

    public double removeLast() {
        if (size == 0) throw new IllegalStateException("Buffer empty");
        double val = data[--size];
        if (size > 0 && size == data.length / 4 && data.length / 2 >= 4) {
            resize(data.length / 2);
        }
        return val;
    }

    private void resize(int newCap) {
        double[] newData = new double[newCap];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            totalCopies++;
        }
        data = newData;
    }

    public double get(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        return data[i];
    }

    public double averageOfLast(int k) {
        if (k <= 0 || k > size) throw new IllegalArgumentException("Invalid k");
        double sum = 0;
        for (int i = size - k; i < size; i++) {
            sum += data[i];
        }
        return sum / k;
    }

    public int size() { return size; }
    public int capacity() { return data.length; }
    public long getTotalCopies() { return totalCopies; }

    public static void main(String[] args) {
        Q3_TelemetryBuffer buf = new Q3_TelemetryBuffer();
        for (int i = 1; i <= 9; i++) buf.append(i);
        System.out.println("After 9 appends -> Size: " + buf.size() + ", Cap: " + buf.capacity() + ", Copies: " + buf.getTotalCopies());

        for (int i = 0; i < 6; i++) buf.removeLast();
        System.out.println("After 6 removes -> Size: " + buf.size() + ", Cap: " + buf.capacity() + ", Copies: " + buf.getTotalCopies());

        Q3_TelemetryBuffer testBuf = new Q3_TelemetryBuffer();
        Random rand = new Random(42);
        int ops = 1000000;
        for (int i = 0; i < ops; i++) {
            if (testBuf.size() == 0 || rand.nextBoolean()) {
                testBuf.append(rand.nextDouble());
            } else {
                testBuf.removeLast();
            }
        }
        System.out.println("1M Ops Copies/Op ratio: " + ((double) testBuf.getTotalCopies() / ops));
    }
}
