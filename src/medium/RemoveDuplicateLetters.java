package medium;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 * 贪心
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) bucket[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        boolean[] contain = new boolean[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']--;
            if (contain[c - 'a']) continue;
            while (sb.length() > 0 && bucket[sb.charAt(sb.length() - 1) - 'a'] > 0 && sb.charAt(sb.length() - 1) > c) {
                contain[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            contain[c - 'a'] = true;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters r = new RemoveDuplicateLetters();
        System.out.println(r.removeDuplicateLetters("bbcaac"));
        System.out.println(r.removeDuplicateLetters("cbacdcbc"));
    }
}
