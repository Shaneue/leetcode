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
 *
 * 注意连续两个字符相同可以选择跳过，减少复杂度
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        int left, right;
        int l = 0, r = 0;
        int current = 0;
        while (current < s.length()) {
            left = current;
            current++;
            while (current < s.length() && s.charAt(current) == s.charAt(current - 1)) {
                current++;
            }
            right = current;
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
