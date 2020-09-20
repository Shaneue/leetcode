package medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int ret = 0;
        int start = Integer.MIN_VALUE;
        for (int[] p : pairs) {
            if (p[0] > start) {
                ret++;
                start = p[1];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumLengthOfPairChain m = new MaximumLengthOfPairChain();
        System.out.println(m.findLongestChain(new int[][]{{-10, -8}, {8, 9}, {-5, 0}, {6, 10}, {-6, -4}, {1, 7}, {9, 10}, {-4, 7}}));
        System.out.println(m.findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
    }
}
