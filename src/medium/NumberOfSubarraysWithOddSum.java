package medium;

/**
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 * <p>
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 * Example 2:
 * <p>
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 * Example 3:
 * <p>
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 * Example 4:
 * <p>
 * Input: arr = [100,100,99,99]
 * Output: 4
 * Example 5:
 * <p>
 * Input: arr = [7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 * <p>
 * 统计单双的个数时，正着与反着数是一样的，所以直接用sum+=n即可
 */
public class NumberOfSubarraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        long sum = 0, c = 0, evens = 0, odds = 0;
        for (int n : arr) {
            sum += n;
            if ((sum & 1) == 1) {
                c += evens + 1;
                odds++;
            } else {
                c += odds;
                evens++;
            }
        }
        return (int) (c % (1e9 + 7));
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfSubarraysWithOddSum().numOfSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(new NumberOfSubarraysWithOddSum().numOfSubarrays(new int[]{7}));
        System.out.println(new NumberOfSubarraysWithOddSum().numOfSubarrays(new int[]{100, 100, 99, 99}));
        System.out.println(new NumberOfSubarraysWithOddSum().numOfSubarrays(new int[]{1, 3, 5}));
        System.out.println(new NumberOfSubarraysWithOddSum().numOfSubarrays(new int[]{2, 4, 6}));
    }
}
