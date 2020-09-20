package medium;

/**
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
 *
 * Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
 *
 * Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 * Example 2:
 *
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 * Example 3:
 *
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 * Example 4:
 *
 * Input: [3,-2,2,-3]
 * Output: 3
 * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
 * Example 5:
 *
 * Input: [-2,-3,-1]
 * Output: -1
 * Explanation: Subarray [-1] has maximum sum -1
 *
 *
 * Note:
 *
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        int ret = -30000;
        int c = -30000;
        int[] preSum = new int[A.length];
        int[] max = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            c = Math.max(A[i], c + A[i]);
            ret = Math.max(ret, c);
            preSum[i] = (i == 0 ? 0 : preSum[i - 1]) + A[i];
            max[i] = Math.max(preSum[i], i == 0 ? -30000 : max[i - 1]);
        }
        for (int i = 0; i < A.length; i++) {
            ret = Math.max(preSum[A.length - 1] - preSum[i] + max[i], ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumSumCircularSubarray m = new MaximumSumCircularSubarray();
        System.out.println(m.maxSubarraySumCircular(new int[]{-2, -3, -1}));
        System.out.println(m.maxSubarraySumCircular(new int[]{1, -2, 3, -2}));
        System.out.println(m.maxSubarraySumCircular(new int[]{3, -2, 2, -3}));
    }
}
