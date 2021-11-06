package medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 * sign初始化必须为+
 */
public class BasicCalculator2 {
    public int calculate(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        int v = 0;
        int ret = 0;

        char sign = '+';

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                v = v * 10 + s.charAt(i) - '0';
            }
            if ((s.charAt(i) < '0' && s.charAt(i) != ' ') || i == s.length() - 1) {
                if (sign == '+') stack.push(v);
                if (sign == '-') stack.push(-v);

                if (sign == '*' || sign == '/') {
                    int t = sign == '*' ? stack.pop() * v : stack.pop() / v;
                    stack.push(t);
                }
                sign = s.charAt(i);
                v = 0;
            }
        }
        while (!stack.isEmpty()) {
            ret += stack.pop();
        }

        return ret;
    }

    public static void main(String[] args) {
        BasicCalculator2 b = new BasicCalculator2();
        System.out.println(b.calculate("3*5"));
    }
}
