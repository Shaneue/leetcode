package medium;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int current = 1;
        int size = n * n;
        int x = -1, y = -1;
        while (current <= size) {
            for (x += 1, y += 1; y < n && matrix[x][y] == 0; y++, current++) {
                matrix[x][y] = current;
            }
            for (x += 1, y -= 1; x < n && matrix[x][y] == 0; x++, current++) {
                matrix[x][y] = current;
            }
            for (x -= 1, y -= 1; y > -1 && matrix[x][y] == 0; y--, current++) {
                matrix[x][y] = current;
            }
            for (x -= 1, y += 1; x > -1 && matrix[x][y] == 0; x--, current++) {
                matrix[x][y] = current;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] test1 = new int[1][1];
        test1[0][0] = 1;
        int[][] test2 = new int[4][4];
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {12, 13, 14, 5};
        int[] a3 = {11, 16, 15, 6};
        int[] a4 = {10, 9, 8, 7};
        test2[0] = a1;
        test2[1] = a2;
        test2[2] = a3;
        test2[3] = a4;
        //test1
        int[][] matrix1 = new SpiralMatrix().generateMatrix(1);
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != test1[i][j]) assert false;
            }
        }
        //test2
        int[][] matrix2 = new SpiralMatrix().generateMatrix(1);
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                if (matrix2[i][j] != test2[i][j]) assert false;
            }
        }
    }
}
