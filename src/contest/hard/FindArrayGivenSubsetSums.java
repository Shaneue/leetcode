package contest.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given an integer n representing the length of an unknown array that you are trying to recover. You are also given an array sums containing the values of all 2n subset sums of the unknown array (in no particular order).
 *
 * Return the array ans of length n representing the unknown array. If multiple answers exist, return any of them.
 *
 * An array sub is a subset of an array arr if sub can be obtained from arr by deleting some (possibly zero or all) elements of arr. The sum of the elements in sub is one possible subset sum of arr. The sum of an empty array is considered to be 0.
 *
 * Note: Test cases are generated such that there will always be at least one correct answer.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, sums = [-3,-2,-1,0,0,1,2,3]
 * Output: [1,2,-3]
 * Explanation: [1,2,-3] is able to achieve the given subset sums:
 * - []: sum is 0
 * - [1]: sum is 1
 * - [2]: sum is 2
 * - [1,2]: sum is 3
 * - [-3]: sum is -3
 * - [1,-3]: sum is -2
 * - [2,-3]: sum is -1
 * - [1,2,-3]: sum is 0
 * Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3] will also be accepted.
 * Example 2:
 *
 * Input: n = 2, sums = [0,0,0,0]
 * Output: [0,0]
 * Explanation: The only correct answer is [0,0].
 * Example 3:
 *
 * Input: n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
 * Output: [0,-1,4,5]
 * Explanation: [0,-1,4,5] is able to achieve the given subset sums.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 15
 * sums.length == 2^n
 * -10^4 <= sums[i] <= 10^4
 *
 * 最小两个数的差就是当前层级的数
 */
public class FindArrayGivenSubsetSums {
    public int[] recoverArray(int n, int[] sums) {
        int[] ret = new int[n];
        Arrays.sort(sums);
        generate(ret, 0, Arrays.stream(sums).boxed().collect(Collectors.toList()));
        return ret;
    }

    void generate(int[] ret, int c, List<Integer> currentLevel) {
        if (currentLevel.size() == 1) return;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int k = 0;
        boolean containsZero = false;
        for (int i = 0; i < currentLevel.size(); i++) {
            if (right.size() > k && right.get(k).equals(currentLevel.get(i))) {
                k++;
            } else {
                left.add(currentLevel.get(i));
                if (currentLevel.get(i) == 0) {
                    containsZero = true;
                }
                right.add(currentLevel.get(i) + currentLevel.get(1) - currentLevel.get(0));
            }
        }
        ret[c++] = containsZero ? currentLevel.get(1) - currentLevel.get(0) : currentLevel.get(0) - currentLevel.get(1);
        generate(ret, c, containsZero ? left : right);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindArrayGivenSubsetSums().recoverArray(3, new int[]{365, 44, -355, 399, 409, 764, 10, 0})));
        System.out.println(Arrays.toString(new FindArrayGivenSubsetSums().recoverArray(3, new int[]{-3, -2, -1, 0, 0, 1, 2, 3})));
        System.out.println(Arrays.toString(new FindArrayGivenSubsetSums().recoverArray(2, new int[]{0, 0, 0, 0})));
        System.out.println(Arrays.toString(new FindArrayGivenSubsetSums().recoverArray(4, new int[]{0, 0, 5, 5, 4, -1, 4, 9, 9, -1, 4, 3, 4, 8, 3, 8})));
    }
}
