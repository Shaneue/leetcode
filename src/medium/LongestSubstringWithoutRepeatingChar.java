package medium;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        int ret = 0;
        int length = 0;
        int start = 1;
        int[] position = new int[128];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (position[c] >= start) {
                start = position[c] + 1;
                length = i - start + 2;
            } else {
                length++;
                if (length > ret) {
                    ret = length;
                }
            }
            position[c] = i + 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingChar().lengthOfLongestSubstring("aab"));
    }
}
