package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 *
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 *
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 * Example 2:
 *
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 *
 *
 * Note:
 *
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 *
 * dp[i][j]=dp[k][i]+1, A[k]+A[i]=A[j]
 */
public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] A) {
        int ret = 2;
        int l = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l; i++) map.put(A[i], i);
        int[][] dp = new int[l][l];
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                int diff = A[j] - A[i];
                if (diff >= A[i]) break;
                if (map.get(diff) != null) {
                    int idx = map.get(diff);
                    if (idx < i) {
                        dp[i][j] = Math.max(dp[i][j], (dp[idx][i] == 0 ? 2 : dp[idx][i]) + 1);
                        ret = Math.max(ret, dp[i][j]);
                    }
                }
            }
        }
        return ret == 2 ? 0 : ret;
    }

    public static void main(String[] args) {
        LengthOfLongestFibonacciSubsequence l = new LengthOfLongestFibonacciSubsequence();
        System.out.println(l.lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50}));
        System.out.println(l.lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18}));
        System.out.println(l.lenLongestFibSubseq(new int[]{1, 3, 7}));
    }
}
