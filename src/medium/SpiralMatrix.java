package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int c = n * m;
        List<Integer> ret = new ArrayList<>();
        int x = -1, y = -1;
        int xLimit = -1, yLimit = -1;
        while (c > 0) {
            for (x += 1, y += 1; x < n && c > 0; x++, c--) {
                ret.add(matrix[y][x]);
            }
            yLimit++;
            for (y += 1, x -= 1; y < m && c > 0; y++, c--) {
                ret.add(matrix[y][x]);
            }
            n--;
            for (x -= 1, y -= 1; x > xLimit && c > 0; x--, c--) {
                ret.add(matrix[y][x]);
            }
            m--;
            for (x += 1, y -= 1; y > yLimit && c > 0; y--, c--) {
                ret.add(matrix[y][x]);
            }
            xLimit++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1}}));
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{}));
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
