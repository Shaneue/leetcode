package medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 *
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 *
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 *
 *
 * Note:
 *
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 *
 * 贪心，田忌赛马
 * 给b数组排序时要记住原位置
 */
public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        int[][] pair = new int[B.length][2];
        for (int i = 0; i < B.length; i++) {
            pair[i][0] = B[i];
            pair[i][1] = i;
        }
        Arrays.sort(pair, Comparator.comparingInt(i -> i[0]));
        Arrays.sort(A);
        int[] ret = new int[A.length];
        int l = 0, r = A.length - 1;
        for (int i = pair.length - 1; i >= 0; i--) {
            if (A[r] > pair[i][0]) ret[pair[i][1]] = A[r--];
            else ret[pair[i][1]] = A[l++];
        }
        return ret;
    }
}
