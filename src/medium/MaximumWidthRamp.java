package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 *
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 * Example 2:
 *
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 *
 * Note:
 *
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 *
 * 不需要用二分搜索，直接贪心即可
 */
public class MaximumWidthRamp {

    public int maxWidthRamp(int[] A) {
        LinkedList<Integer> q = new LinkedList<>();
        int ret = 0, n = A.length;
        for (int i = 0; i < n; ++i)
            if (q.isEmpty() || A[q.peekLast()] > A[i])
                q.add(i);
        for (int i = n - 1; i > ret; --i)
            while (!q.isEmpty() && A[q.peekLast()] <= A[i])
                ret = Math.max(ret, i - q.removeLast());
        return ret;
    }

    public int maxWidthRampBinarySearch(int[] A) {
        List<int[]> dec = new ArrayList<>();
        int ret = 1;
        dec.add(new int[]{A[0], 0});
        for (int i = 1; i < A.length; i++) {
            if (dec.get(dec.size() - 1)[0] > A[i]) dec.add(new int[]{A[i], i});
            else if (dec.get(0)[0] <= A[i]) {
                ret = i;
            } else {
                int idx = Collections.binarySearch(dec, new int[]{A[i], i}, (a, b) -> Integer.compare(b[0], a[0]));
                if (idx < 0) idx = -idx - 1;
                idx = dec.get(idx)[1];
                ret = Math.max(i - idx, ret);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumWidthRamp m = new MaximumWidthRamp();
        System.out.println(m.maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
    }
}
