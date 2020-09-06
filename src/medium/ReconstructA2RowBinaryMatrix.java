package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the following details of a matrix with n columns and 2 rows :
 *
 * The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
 * The sum of elements of the 0-th(upper) row is given as upper.
 * The sum of elements of the 1-st(lower) row is given as lower.
 * The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 *
 * Return it as a 2-D integer array.
 *
 * If there are more than one valid solution, any of them will be accepted.
 *
 * If no valid solution exists, return an empty 2-D array.
 *
 *
 *
 * Example 1:
 *
 * Input: upper = 2, lower = 1, colsum = [1,1,1]
 * Output: [[1,1,0],[0,0,1]]
 * Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
 * Example 2:
 *
 * Input: upper = 2, lower = 3, colsum = [2,2,1,1]
 * Output: []
 * Example 3:
 *
 * Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 *
 *
 * Constraints:
 *
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 *
 * 先统计2的个数
 */
public class ReconstructA2RowBinaryMatrix {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> up = new ArrayList<>();
        List<Integer> low = new ArrayList<>();
        int c = 0;
        for (int i : colsum) c += i == 2 ? 1 : 0;
        for (int i : colsum) {
            if (i == 2) {
                if (upper > 0 && lower > 0) {
                    up.add(1);
                    upper--;
                    low.add(1);
                    lower--;
                    c--;
                } else return new ArrayList<>();
            } else if (i == 0) {
                up.add(0);
                low.add(0);
            } else {
                if (upper < c && lower < c) return new ArrayList<>();
                if (upper > c) {
                    up.add(1);
                    upper--;
                    low.add(0);
                } else {
                    low.add(1);
                    lower--;
                    up.add(0);
                }

            }
        }
        if (upper != 0 || lower != 0) return new ArrayList<>();
        ret.add(up);
        ret.add(low);
        return ret;
    }

    public static void main(String[] args) {
        ReconstructA2RowBinaryMatrix r = new ReconstructA2RowBinaryMatrix();
        System.out.println(r.reconstructMatrix(5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1}));
    }
}
