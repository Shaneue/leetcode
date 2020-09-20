package medium;

/**
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 *
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 */
public class NumberOfSubarraysWithBoundedMaximum {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int ret = 0;
        int outbound = -1;
        int inbound = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) {
                outbound = i;
            } else if (A[i] >= L && A[i] <= R) {
                inbound = i;
                ret += inbound - outbound;
            } else if (A[i] < L && inbound > outbound) {
                // 保证该值不会是子数组最大值
                ret += inbound - outbound;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        NumberOfSubarraysWithBoundedMaximum n = new NumberOfSubarraysWithBoundedMaximum();
        System.out.println(n.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println(n.numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
    }
}
