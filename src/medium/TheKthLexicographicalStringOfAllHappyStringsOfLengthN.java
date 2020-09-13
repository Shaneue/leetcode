package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * Example 2:
 *
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 *
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 * Example 4:
 *
 * Input: n = 2, k = 7
 * Output: ""
 * Example 5:
 *
 * Input: n = 10, k = 100
 * Output: "abacbabacb"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10
 * 1 <= k <= 100
 *
 * 可暴力求解
 */
public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public String getHappyString(int n, int k) {
        List<String> ret = new ArrayList<>();
        dfs(ret, n, k, new StringBuilder());
        if (ret.size() < k) return "";
        return ret.get(k - 1);
    }

    void dfs(List<String> ret, int n, int k, StringBuilder s) {
        if (ret.size() > k) return;
        if (s.length() == n) {
            ret.add(s.toString());
            return;
        }
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (s.length() == 0 || s.charAt(s.length() - 1) != c) {
                s.append(c);
                dfs(ret, n, k, s);
                s.deleteCharAt(s.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        TheKthLexicographicalStringOfAllHappyStringsOfLengthN t = new TheKthLexicographicalStringOfAllHappyStringsOfLengthN();
        System.out.println(t.getHappyString(3, 9));
        System.out.println(t.getHappyString(3, 7));
        System.out.println(t.getHappyString(10, 100));
    }
}
