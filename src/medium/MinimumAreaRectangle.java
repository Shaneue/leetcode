package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 * <p>
 * If there isn't any rectangle, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 * <p>
 * Note:
 * <p>
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 */
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            Set<Integer> set = map.getOrDefault(p[0], new HashSet<>());
            set.add(p[1]);
            map.put(p[0], set);
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1] && map.get(points[i][0]).contains(points[j][1]) && map.get(points[j][0]).contains(points[i][1])) {
                    ret = Math.min(ret, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                }
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }

    public static void main(String[] args) {
        MinimumAreaRectangle m = new MinimumAreaRectangle();
        System.out.println(m.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}));
        System.out.println(m.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}}));
        System.out.println(m.minAreaRect(new int[][]{{1, 1}}));
    }
}
