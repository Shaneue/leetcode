package hard;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2,
 * Output: 3
 * Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^4
 *
 * 最优解使用归并排序
 * pre[]数组最前面必须加一个0
 * n的前一个下标刚好<=upper，m的下标刚好是第一个大于等于lower
 */
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0) return 0;
        long[] pre = new long[nums.length + 1];
        pre[0] = 0;
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        return merge(pre, lower, upper, 0, pre.length - 1, new long[pre.length]);
    }

    int merge(long[] pre, int lower, int upper, int l, int r, long[] temp) {
        if (l == r) return 0;
        int mid = (l + r) / 2;
        int count = 0;
        count += merge(pre, lower, upper, l, mid, temp);
        count += merge(pre, lower, upper, mid + 1, r, temp);
        int m, n;
        m = n = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (m <= r && pre[m] - pre[i] < lower) m++;
            while (n <= r && pre[n] - pre[i] <= upper) n++;
            count += n - m;
        }
        //merge sort
        int i = l, j = mid + 1, s = l;
        while (i <= mid && j <= r) temp[s++] = pre[i] < pre[j] ? pre[i++] : pre[j++];
        while (i <= mid) temp[s++] = pre[i++];
        while (j <= r) temp[s++] = pre[j++];
        for (i = l; i <= r; i++) {
            pre[i] = temp[i];
        }
        return count;
    }

    public static void main(String[] args) {
        CountOfRangeSum c = new CountOfRangeSum();
        System.out.println(c.countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }
}
