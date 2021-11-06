package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary string s and an integer k.
 *
 * Return true if every binary code of length k is a substring of s. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 * Example 2:
 *
 * Input: s = "00110", k = 2
 * Output: true
 * Example 3:
 *
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 * Example 4:
 *
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
 * Example 5:
 *
 * Input: s = "0000000001011100", k = 4
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^5
 * s[i] is either '0' or '1'.
 * 1 <= k <= 20
 *
 * 用数组去过滤，不需要一直用parseInt
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() - k + 1 < (1 << k)) return false;
        int c = 0, target = 1 << k, value = 0;
        int and = (1 << k) - 1;
        boolean[] filter = new boolean[target];
        for (int i = 0; i + k <= s.length(); i++) {
            if (i == 0) {
                value = Integer.parseInt(s.substring(i, i + k), 2);
            } else {
                value = ((value << 1) & and) | (s.charAt(i + k - 1) - '0');
            }
            if (!filter[value]) {
                filter[value] = true;
                if (++c == target) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new CheckIfAStringContainsAllBinaryCodesOfSizeK().hasAllCodes("00110", 2);
    }
}
