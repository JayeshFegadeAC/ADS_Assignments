class SimpleStack {
    String[] arr = new String[5];
    int top = -1;

    void push(String functionName) {
        if (top == arr.length - 1) {
            System.out.println("Stack Overflow!");
        } else {
            top++;
            arr[top] = functionName;
        }
    }

    void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow!");
        } else {
            System.out.println("Popped: " + arr[top]);
            top--;
        }
    }

    void displayCrashReport() {
        System.out.println("\n--- Stack Trace (Crash Report) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println("at function: " + arr[i]);
        }
    }
}

public class CrashReportStack {
    public static void main(String[] args) {
        SimpleStack stack = new SimpleStack();
        
        stack.push("main()");
        stack.push("calculateData()");
        stack.push("divideByZero()");

        stack.displayCrashReport();
    }
}