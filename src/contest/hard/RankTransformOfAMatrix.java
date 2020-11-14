package contest.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 *
 * If an element is the smallest element in its row and column, then its rank is 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * It is guaranteed that answer is unique under the given rules.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 * Example 2:
 *
 *
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * Example 3:
 *
 *
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 * Example 4:
 *
 *
 * Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
 * Output: [[5,1,4],[1,2,3],[6,3,1]]
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -10^9 <= matrix[row][col] <= 10^9
 *
 * 有重复元素的时候，需要用并查集
 */
public class RankTransformOfAMatrix {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<int[]> list = map.getOrDefault(matrix[i][j], new ArrayList<>());
                list.add(new int[]{i, j});
                map.put(matrix[i][j], list);
            }
        }

        int[] rank = new int[m + n];
        for (List<int[]> list : map.values()) {
            int[] rankTemp = Arrays.copyOf(rank, rank.length);
            // union-find handles duplicates
            int[] f = new int[m + n];
            for (int i = 0; i < f.length; i++) f[i] = i;
            for (int[] p : list) {
                int x = root(f, p[0]);
                int y = root(f, p[1] + m);
                f[x] = y;
                rankTemp[y] = Math.max(rankTemp[x], rankTemp[y]);
            }
            for (int[] p : list) {
                rank[p[0]] = rank[p[1] + m] = matrix[p[0]][p[1]] = rankTemp[root(f, p[0])] + 1;
            }
        }
        return matrix;
    }

    int root(int[] f, int x) {
        if (f[x] != x) f[x] = root(f, f[x]);
        return f[x];
    }


    public static void main(String[] args) {
        RankTransformOfAMatrix r = new RankTransformOfAMatrix();
        System.out.println(Arrays.deepToString(r.matrixRankTransform(new int[][]{{7, 3, 6}, {1, 4, 5}, {9, 8, 2}})));
        System.out.println(Arrays.deepToString(r.matrixRankTransform(new int[][]{{20, -21, 14}, {-19, 4, 19}, {22, -47, 24}, {-19, 4, 19}})));
        System.out.println(Arrays.deepToString(r.matrixRankTransform(new int[][]{{1, 1}, {1, 1}})));
        System.out.println(Arrays.deepToString(r.matrixRankTransform(new int[][]{{1, 2}, {3, 4}})));
    }
}
