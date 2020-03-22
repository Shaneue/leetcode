package medium;

import java.util.Arrays;
import java.util.Comparator;

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
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int re = 0;
        int previous = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0) {
                re++;
                previous = intervals[i][1];
                continue;
            }
            if (intervals[i][1] > previous) {
                re++;
                previous = intervals[i][1];
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {2, 5}, {0, 4}};
        System.out.println(new RemoveCoveredIntervals().removeCoveredIntervals(intervals));
    }
}
