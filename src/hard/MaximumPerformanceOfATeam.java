package hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively. Return the maximum performance of a team composed of at most k engineers, since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 * 1 <= k <= n
 *
 * 贪心，先取efficiency最高的k个，然后逐个将speed最小的剔除，加入新的pair，取最值
 * mod要先取整，要不有误差
 */
public class MaximumPerformanceOfATeam {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] pairs = new int[n][2];
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < n; i++) {
            pairs[i][0] = speed[i];
            pairs[i][1] = efficiency[i];
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0;
        long ret = 0;
        for (int i = 0; i < k; i++) {
            sum += pairs[i][0];
            ret = Math.max(ret, sum * pairs[i][1]);
            pq.add(pairs[i][0]);
        }
        for (int i = k; i < n; i++) {
            sum -= pq.remove();
            pq.add(pairs[i][0]);
            sum += pairs[i][0];
            ret = Math.max(ret, sum * pairs[i][1]);
        }
        return (int) (ret % mod);
    }

    public static void main(String[] args) {
        MaximumPerformanceOfATeam m = new MaximumPerformanceOfATeam();
        System.out.println(m.maxPerformance(3, new int[]{2, 8, 2}, new int[]{2, 7, 1}, 2));
        System.out.println(m.maxPerformance(1, new int[]{2}, new int[]{5}, 1));
        System.out.println(m.maxPerformance(2, new int[]{2, 10}, new int[]{5, 4}, 2));
        System.out.println(m.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 2));
        System.out.println(m.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 3));
        System.out.println(m.maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 4));
    }
}
