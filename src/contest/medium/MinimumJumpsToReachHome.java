package contest.medium;

import java.util.LinkedList;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 * Example 2:
 *
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 * Example 3:
 *
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 *
 *
 * Constraints:
 *
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * All the elements in forbidden are distinct.
 * Position x is not forbidden.
 */
public class MinimumJumpsToReachHome {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        boolean[][] visited = new boolean[8888][2];
        for (int i : forbidden) visited[i][0] = visited[i][1] = true;
        int ret = 0;
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] p = q.poll();
                if (p[0] == x) return ret;
                int idx = p[0] - b;
                if (idx >= 0 && !visited[idx][1] && p[1] == 0) {
                    q.add(new int[]{idx, 1});
                    visited[idx][1] = true;
                }
                idx = p[0] + a;
                if (!visited[idx][0] && idx <= 6000) {
                    q.add(new int[]{idx, 0});
                    visited[idx][0] = true;
                }
            }
            ret++;
        }
        return -1;
    }

    public static void main(String[] args) {
        MinimumJumpsToReachHome m = new MinimumJumpsToReachHome();
        System.out.println(m.minimumJumps(new int[]{8, 3, 16, 6, 12, 20}, 15, 13, 11));
    }
}
