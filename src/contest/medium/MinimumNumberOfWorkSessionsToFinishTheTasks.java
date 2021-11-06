package contest.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * Example 2:
 *
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * Example 3:
 *
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 *
 *
 * Constraints:
 *
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 *
 * 需要记住一个session的内容且当前的最小值，因为这个最小值不是确定的，然后最小值为0的话可以停止当前的搜索
 */
public class MinimumNumberOfWorkSessionsToFinishTheTasks {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        boolean[] used = new boolean[n];
        Arrays.sort(tasks);
        int ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (!used[i]) {
                List<Integer>[] session = new List[]{new ArrayList<>()};
                used[i] = true;
                searchForSession(tasks, used, session, new ArrayList<>(), sessionTime, i, tasks[i], new int[]{Integer.MAX_VALUE});
                for (int j : session[0]) used[j] = true;
                ret++;
            }
        }
        return ret;
    }

    private void searchForSession(int[] tasks, boolean[] used, List<Integer>[] session, List<Integer> l, int target, int current, int sum, int[] min) {
        if (sum > target) return;
        if (target - sum < min[0]) {
            min[0] = target - sum;
            session[0] = new ArrayList<>(l);
        }
        if (min[0] == 0) return;
        for (int i = current - 1; i >= 0; i--) {
            if (!used[i]) {
                used[i] = true;
                l.add(i);
                searchForSession(tasks, used, session, l, target, i, sum + tasks[i], min);
                l.remove(l.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfWorkSessionsToFinishTheTasks().minSessions(new int[]{3, 1, 3, 1, 1}, 8));
    }
}
