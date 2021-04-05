package contest.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an even integer n​​​​​​. You initially have a permutation perm of size n​​ where perm[i] == i​ (0-indexed)​​​​.
 *
 * In one operation, you will create a new array arr, and for each i:
 *
 * If i % 2 == 0, then arr[i] = perm[i / 2].
 * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * You will then assign arr​​​​ to perm.
 *
 * Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its initial value.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: prem = [0,1] initially.
 * After the 1st operation, prem = [0,1]
 * So it takes only 1 operation.
 * Example 2:
 *
 * Input: n = 4
 * Output: 2
 * Explanation: prem = [0,1,2,3] initially.
 * After the 1st operation, prem = [0,2,1,3]
 * After the 2nd operation, prem = [0,1,2,3]
 * So it takes only 2 operations.
 * Example 3:
 *
 * Input: n = 6
 * Output: 4
 *
 *
 * Constraints:
 *
 * 2 <= n <= 1000
 * n​​​​​​ is even.
 */
public class MinimumNumberOfOperationsToReinitializeAPermutation {
    public int reinitializePermutation(int n) {
        Set<Integer> test = new HashSet<>();
        int ret = 0;
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) perm[i] = i;
        while (true) {
            ret++;
            int[] t = new int[n];
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    t[i] = perm[i / 2];
                } else {
                    t[i] = perm[n / 2 + (i - 1) / 2];

                }
                if (t[i] != i) test.add(i);
                else test.remove(i);
            }
            perm = t;
            if (test.size() == 0) return ret;
        }
    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToReinitializeAPermutation m = new MinimumNumberOfOperationsToReinitializeAPermutation();
        System.out.println(m.reinitializePermutation(4));
    }
}
