package hard;

import java.util.LinkedList;

/**
 * Given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 *
 * Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length. It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 * Example 2:
 *
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 *
 *
 * Constraints:
 *
 * 2 <= points.length <= 10^5
 * points[i].length == 2
 * -10^8 <= points[i][0], points[i][1] <= 10^8
 * 0 <= k <= 2 * 10^8
 * points[i][0] < points[j][0] for all 1 <= i < j <= points.length
 * xi form a strictly increasing sequence.
 */
public class MaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        LinkedList<Integer> q = new LinkedList<>();
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            while (!q.isEmpty() && points[i][0] - points[q.peekFirst()][0] > k) q.removeFirst();
            if (!q.isEmpty())
                ret = Math.max(ret, points[q.peekFirst()][1] - points[q.peekFirst()][0] + points[i][0] + points[i][1]);
            while (!q.isEmpty() && points[q.peekLast()][1] - points[q.peekLast()][0] <= points[i][1] - points[i][0])
                q.removeLast();
            q.add(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxValueOfEquation m = new MaxValueOfEquation();
        System.out.println(m.findMaxValueOfEquation(new int[][]{{-19, -12}, {-13, -18}, {-12, 18}, {-11, -8}, {-8, 2}, {-7, 12}, {-5, 16}, {-3, 9}, {1, -7}, {5, -4}, {6, -20}, {10, 4}, {16, 4}, {19, -9}, {20, 19}}, 6));
        System.out.println(m.findMaxValueOfEquation(new int[][]{{0, 0}, {3, 0}, {9, 2}}, 3));
    }
}
