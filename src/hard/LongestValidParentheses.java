package hard;

import java.util.LinkedList;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 *
 * 注意start
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ret = 0, start = 0;
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') q.push(i);
            else if (q.isEmpty()) start = i + 1;
            else {
                q.pop();
                ret = Math.max(ret, q.isEmpty() ? i - start + 1 : i - q.peek());
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        System.out.println(l.longestValidParentheses("(())()"));
        System.out.println(l.longestValidParentheses("(()()"));
        System.out.println(l.longestValidParentheses("()(()"));
        System.out.println(l.longestValidParentheses("()"));
        System.out.println(l.longestValidParentheses("("));
        System.out.println(l.longestValidParentheses("(())"));
    }
}
