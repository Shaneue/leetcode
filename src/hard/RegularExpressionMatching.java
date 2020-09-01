package hard;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * 多种情况需要整合
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        return match(s, p);
    }

    boolean match(String s, int i, String p, int j) {
        if (j == -1) return i == -1;
        boolean ret = false;
        if (p.charAt(j) == '*') {
            if (i > -1 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i))) {
                ret = match(s, i - 1, p, j);
            }
            if (!ret) ret = match(s, i, p, j - 2);
        }
        if (i > -1 && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
            if (!ret) ret = match(s, i - 1, p, j - 1);
        }
        return ret;
    }

    boolean match(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        boolean[][] dp = new boolean[ls + 1][lp + 1];
        dp[0][0] = true;
        for (int j = 1; j <= lp; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j] || dp[i][j - 2];
                }
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[ls][lp];
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.isMatch("aaa", "ab*a*c*a"));
        System.out.println(r.isMatch("aab", "c*a*b"));
    }
}
