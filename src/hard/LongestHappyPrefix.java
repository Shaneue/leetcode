package hard;

/**
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 *
 * Given a string s. Return the longest happy prefix of s .
 *
 * Return an empty string if no such prefix exists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
 * Example 2:
 *
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 * Example 3:
 *
 * Input: s = "leetcodeleet"
 * Output: "leet"
 * Example 4:
 *
 * Input: s = "a"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 *
 * rolling hash还是太慢
 * 用dp记住当前匹配的串里有没有能够匹配prefix的suffix
 */
public class LongestHappyPrefix {
    public String longestPrefix(String s) {
        int[] dp = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(j))
                dp[i] = ++j;
            else if (j > 0) {
                j = dp[j - 1];
                i--;
            }
        }
        return s.substring(0, j);
    }

    public static void main(String[] args) {
        LongestHappyPrefix l = new LongestHappyPrefix();
        System.out.println(l.longestPrefix("acccbaaacccbaac"));
    }
}
