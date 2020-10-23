package contest.easy;

import java.util.Arrays;

/**
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 * Example 2:
 *
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * Example 3:
 *
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 * Example 4:
 *
 * Input: s = "cabbac"
 * Output: 4
 * Explanation: The optimal substring here is "abba". Other non-optimal substrings include "bb" and "".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 */
public class LargestSubstringBetweenTwoEqualCharacters {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] p = new int[128];
        Arrays.fill(p, -1);
        char[] chars = s.toCharArray();
        int ret = -1;
        for (int i = 0; i < chars.length; i++) {
            if (p[chars[i]] == -1) p[chars[i]] = i;
            else ret = Math.max(ret, i - p[chars[i]] - 1);
        }
        return ret < 0 ? -1 : ret;
    }

    public static void main(String[] args) {
        LargestSubstringBetweenTwoEqualCharacters l = new LargestSubstringBetweenTwoEqualCharacters();
        System.out.println(l.maxLengthBetweenEqualCharacters("aa"));
        System.out.println(l.maxLengthBetweenEqualCharacters("c"));
        System.out.println(l.maxLengthBetweenEqualCharacters("cabbac"));
    }
}
