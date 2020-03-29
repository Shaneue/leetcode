package medium;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        int left, right;
        int l = 0, r = 0;
        int start = 0;
        while (start < s.length()) {
            left = start;
            start++;
            while (start < s.length() && s.charAt(start) == s.charAt(start - 1)) {
                start++;
            }
            right = start;
            while (left > 0 && right < s.length() && s.charAt(left - 1) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left + 1 > r - l + 1) {
                l = left;
                r = right;
            }
        }
        return s.substring(l, r);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("aabbcc"));
    }
}
