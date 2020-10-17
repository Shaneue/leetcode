package medium;

import java.util.Arrays;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * <p>
 * After doing so, return the number of remaining intervals.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 */

public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(b[1], a[1]);
            else return Integer.compare(a[0], b[0]);
        });
        int ret = 0;
        int previous = 0;
        for (int[] interval : intervals) {
            if (interval[1] > previous) {
                ret++;
                previous = interval[1];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {1, 4}, {3, 4}};
        System.out.println(new RemoveCoveredIntervals().removeCoveredIntervals(intervals));
    }
}
