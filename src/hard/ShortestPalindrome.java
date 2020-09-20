package hard;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 *
 * Example 2:
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (isPalindrome(s.toCharArray(), s.length() - 1)) return s;
        int j = 0;
        // j之后的字母肯定需要被加到前面
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) j++;
        }
        String suffix = s.substring(j);
        return new StringBuilder(suffix).reverse().append(shortestPalindrome(s.substring(0, j))).append(suffix).toString();
    }

    boolean isPalindrome(char[] chars, int r) {
        int l = 0;
        while (l < r) {
            if (chars[l++] != chars[r--]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ShortestPalindrome s = new ShortestPalindrome();
        System.out.println(s.shortestPalindrome("abcad"));
    }
}
