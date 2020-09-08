package medium;

/**
 * A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
 *
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
 *
 * Return the minimum number of flips to make S monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S only consists of '0' and '1' characters.
 *
 * 最简化的代码有点技巧，count为1的个数，遇到0就翻1一定可以组成单调串，然后和1的个数对比一下取最小
 */
public class FlipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr(String S) {
        int n = S.length(), ret = 0, count = 0;
        for (int i = 0; i < n; ++i) {
            if (S.charAt(i) == '0') {
                ret++;
            } else {
                count++;
            }
            ret = Math.min(ret, count);
        }
        return ret;
    }

    public static void main(String[] args) {
        FlipStringToMonotoneIncreasing f = new FlipStringToMonotoneIncreasing();
        System.out.println(f.minFlipsMonoIncr("10011111110010111011"));
    }
}
