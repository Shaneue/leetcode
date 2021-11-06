package hard;

import java.util.LinkedList;

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *
 * Input: matrix = []
 * Output: 0
 * Example 3:
 *
 * Input: matrix = [['0']]
 * Output: 0
 * Example 4:
 *
 * Input: matrix = [['1']]
 * Output: 1
 * Example 5:
 *
 * Input: matrix = [['0','0']]
 * Output: 0
 *
 *
 * Constraints:
 *
 * rows == matrix.length
 * cols == matrix[i].length
 * 0 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '0' && j > 0) {
                    matrix[i][j] += matrix[i][j - 1] - '0';
                }
            }
        }
        int ret = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            int[] heights = new int[matrix.length + 1];
            for (int i = 0; i < matrix.length; i++) {
                heights[i] = matrix[i][j] - '0';
            }
            heights[matrix.length] = -1;
            LinkedList<Integer> q = new LinkedList<>();
            q.add(-1);
            for (int k = 0; k < heights.length; k++) {
                while (q.peekLast() != -1 && heights[q.peekLast()] > heights[k]) {
                    ret = Math.max(ret, heights[q.pollLast()] * (k - q.peekLast() - 1));
                }
                q.add(k);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}}));
        System.out.println(new MaximalRectangle().maximalRectangle(new char[][]{}));
    }
}
