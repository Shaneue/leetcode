package medium;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2], k = 3
 * Output: 9
 * Example 2:
 *
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * Example 3:
 *
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class KConcatenationMaximumSum {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long max = 0;
        long sum = 0;
        int t = 0;
        int mod = (int) (1e9 + 7);
        if (k == 1) {
            for (int i : arr) {
                t = Math.max(0, t + i);
                max = Math.max(max, t);
            }
        } else {
            for (int j = 0; j < 2; j++) {
                for (int i : arr) {
                    t = Math.max(0, t + i);
                    max = Math.max(max, t);
                }
            }
        }
        if (k > 2) {
            for (int i : arr) {
                sum += i;
            }
        }
        return (int) Math.max(max, k > 2 ? (max + sum * (k - 2)) % mod : 0);
    }

    public static void main(String[] args) {
        KConcatenationMaximumSum k = new KConcatenationMaximumSum();
        System.out.println(k.kConcatenationMaxSum(new int[]{-5, 4, -4, -3, 5, -3}, 3));
    }
}
