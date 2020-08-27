package medium;

import java.util.Arrays;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 *
 * 用栈来处理，下标入栈
 * 没必要用栈，时间复杂度是一样的，直接嵌套迭代即可
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int[] ret = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--)
            for (int j = i - 1; j >= 0 && T[j] < T[i]; j--)
                ret[j] = i - j;
        return ret;
    }

    public static void main(String[] args) {
        DailyTemperatures d = new DailyTemperatures();
        System.out.println(Arrays.toString(d.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
