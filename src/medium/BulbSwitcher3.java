package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.
 *
 * At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color to blue only if it is on and all the previous bulbs (to the left) are turned on too.
 *
 * Return the number of moments in which all turned on bulbs are blue.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: light = [2,1,3,5,4]
 * Output: 3
 * Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
 * Example 2:
 *
 * Input: light = [3,2,4,1,5]
 * Output: 2
 * Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
 * Example 3:
 *
 * Input: light = [4,1,2,3]
 * Output: 1
 * Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
 * Bulb 4th changes to blue at the moment 3.
 * Example 4:
 *
 * Input: light = [2,1,4,3,6,5]
 * Output: 3
 * Example 5:
 *
 * Input: light = [1,2,3,4,5,6]
 * Output: 6
 *
 *
 * Constraints:
 *
 * n == light.length
 * 1 <= n <= 5 * 10^4
 * light is a permutation of  [1, 2, ..., n]
 *
 * 不需要用树状数组
 * 在i时刻，i左边的灯必须全部点亮，假设全亮，则max就是在前i个元素的最大值，且最大值只能是i+1
 */
public class BulbSwitcher3 {
    public int numTimesAllBlue(int[] light) {
        int max = 0, ret = 0;
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            if (max == i + 1) ret++;
        }
        return ret;
    }

    public int numTimesAllBlueBIT(int[] light) {
        int[] f = new int[light.length + 1];
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < light.length; i++) map.put(light[i], i + 1);
        for (int i = 1; i <= light.length; i++) {
            int x = map.get(i);
            update(f, x);
            if (query(f, i) == i) ret++;
        }
        return ret;
    }

    void update(int[] f, int x) {
        for (; x <= f.length - 1; x += x & -x) {
            f[x] += 1;
        }
    }

    int query(int[] f, int x) {
        int sum = 0;
        for (; x > 0; x -= x & -x) {
            sum += f[x];
        }
        return sum;
    }

    public static void main(String[] args) {
        BulbSwitcher3 b = new BulbSwitcher3();
        System.out.println(b.numTimesAllBlue(new int[]{4, 1, 2, 3}));
        System.out.println(b.numTimesAllBlue(new int[]{3, 2, 4, 1, 5}));
        System.out.println(b.numTimesAllBlue(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(b.numTimesAllBlue(new int[]{2, 1, 4, 3, 6, 5}));
    }
}
