package medium;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 *
 * Example 1:
 *
 * Input:
 * s = "aaabb", k = 3
 *
 * Output:
 * 3
 *
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input:
 * s = "ababbc", k = 2
 *
 * Output:
 * 5
 *
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * 通过整体来剪枝局部
 * 采用分治
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int l = 0;
        while (l < s.length()) {
            if (count[s.charAt(l) - 'a'] >= k) l++;
            else break;
        }
        if (l >= s.length()) return l;
        int preLength = longestSubstring(s.substring(0, l), k);
        while (l < s.length() && count[s.charAt(l) - 'a'] < k) {
            // less than k, then pass
            ++l;
        }
        int sufLength = l >= s.length() ? 0 : longestSubstring(s.substring(l), k);
        return Math.max(preLength, sufLength);
    }

    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters l = new LongestSubstringWithAtLeastKRepeatingCharacters();
        System.out.println(l.longestSubstring("ababbc", 2));
        System.out.println(l.longestSubstring("ababacb", 3));
    }
}
