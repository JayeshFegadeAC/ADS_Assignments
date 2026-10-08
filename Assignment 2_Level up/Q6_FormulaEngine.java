import java.util.*;

public class Q6_FormulaEngine {

    public static long evaluate(String expr, Map<String, Long> vars) {
        Stack<Long> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isWhitespace(c)) continue;

            if (Character.isDigit(c)) {
                long val = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = val * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                i--;
                values.push(val);
            } else if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && Character.isLetter(expr.charAt(i))) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                i--;
                String var = sb.toString();
                if (!vars.containsKey(var)) throw new IllegalArgumentException("Unknown variable: " + var);
                values.push(vars.get(var));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (ops.isEmpty()) throw new IllegalArgumentException("Mismatched parentheses");
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            if (ops.peek() == '(') throw new IllegalArgumentException("Mismatched parentheses");
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    private static long applyOp(char op, long b, long a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        String formula = "price*qty-discount+(price*qty*gst)/100";
        Map<String, Long> vars = new HashMap<>();
        vars.put("price", 250L);
        vars.put("qty", 4L);
        vars.put("discount", 100L);
        vars.put("gst", 18L);

        System.out.println("Result: " + evaluate(formula, vars));
    }
}