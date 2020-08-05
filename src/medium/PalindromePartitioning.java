package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        dfs(s, ret, 0, 1, new ArrayList<>());
        return ret;
    }

    void dfs(String s, List<List<String>> ret, int from, int to, List<String> current) {
        if (from == s.length()) ret.add(new ArrayList<>(current));
        if (to > s.length()) return;
        String sub = s.substring(from, to);
        if (isPalindrome(sub)) {
            current.add(sub);
            dfs(s, ret, to, to + 1, current);
            current.remove(current.size() - 1);
        }
        dfs(s, ret, from, to + 1, current);
    }

    boolean isPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(new PalindromePartitioning().partition(s));
    }
}
