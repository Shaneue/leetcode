package hard;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 *
 * java没有自带multiset，所以最好使用binary search，用会TreeMap会稍微复杂点
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ret = new double[nums.length - k + 1];
        int[] window = new int[k];
        System.arraycopy(nums, 0, window, 0, k);
        Arrays.sort(window);
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (1.0 * window[k / 2] + window[(k - 1) / 2]) / 2;
            remove(window, nums[i]);
            if (i + k < nums.length) add(window, nums[i + k]);
        }
        return ret;
    }

    void remove(int[] window, int val) {
        int idx = Arrays.binarySearch(window, val);
        while (idx < window.length - 1) window[idx] = window[++idx];
    }

    void add(int[] window, int val) {
        int idx = Arrays.binarySearch(window, 0, window.length - 1, val);
        if (idx < 0) idx = -idx - 1;
        int t = window.length - 1;
        while (t > idx) window[t] = window[--t];
        window[idx] = val;
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(Arrays.stream(s.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)).boxed().collect(Collectors.toList()));
    }
}
