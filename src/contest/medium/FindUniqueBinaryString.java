package contest.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * Example 2:
 *
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 *
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] is either '0' or '1'.
 */
public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> filter = new HashSet<>();
        for (String s : nums) {
            filter.add(Integer.parseInt(s, 2));
        }
        int max = 1 << nums.length;
        for (int i = 0; i < max; i++) {
            if (!filter.contains(i)) {
                StringBuilder ret = new StringBuilder(Integer.toString(i, 2));
                for (int j = ret.length(); j < nums[0].length(); j++) {
                    ret.insert(0, "0");
                }
                return ret.toString();
            }
        }
        return "";
    }
}
