package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 *
 * Return 0 if the string is already balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 * Example 4:
 *
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 *
 * 用map会影响效率
 */
public class ReplaceTheSubstringForBalancedString {
    public int balancedString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('Q', 0);
        map.put('W', 1);
        map.put('E', 2);
        map.put('R', 3);
        int target = s.length() / 4;
        int[] count = new int[]{-target, -target, -target, -target};
        for (char c : s.toCharArray()) count[map.get(c)]++;
        int ret = s.length();
        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j < s.length() && (count[0] > 0 || count[1] > 0 || count[2] > 0 || count[3] > 0))
                count[map.get(s.charAt(j++))]--;
            if (count[0] <= 0 && count[1] <= 0 && count[2] <= 0 && count[3] <= 0) ret = Math.min(ret, j - i);
            if (ret == 0) break;
            count[map.get(s.charAt(i))]++;
        }
        return ret;
    }
}
