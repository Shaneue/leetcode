package medium;

/**
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: "()"
 * Output: 1
 * Example 2:
 *
 * Input: "(())"
 * Output: 2
 * Example 3:
 *
 * Input: "()()"
 * Output: 2
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 *
 * 直接按照（）来累加
 * 用递归来求也是极好理解的
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String S) {
        int c = 0;
        int ret = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                if (S.charAt(i + 1) == ')')
                    ret += 1 << c;
                c++;
            } else c--;
        }
        return ret;
    }

    public static void main(String[] args) {
        ScoreOfParentheses s = new ScoreOfParentheses();
        System.out.println(s.scoreOfParentheses("((())((())))"));
        System.out.println(s.scoreOfParentheses("()()"));
        System.out.println(s.scoreOfParentheses("(())"));
        System.out.println(s.scoreOfParentheses("()"));
    }
}
