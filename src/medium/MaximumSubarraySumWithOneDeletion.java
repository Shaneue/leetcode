package medium;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 * <p>
 * Note that the subarray needs to be non-empty after deleting one element.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 * Example 2:
 * <p>
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 * Example 3:
 * <p>
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 * <p>
 * 从删除的那个点开始分别计算两边最大值
 */
public class MaximumSubarraySumWithOneDeletion {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] head = new int[n];
        int[] tail = new int[n];
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            head[i] = i == 0 ? arr[i] : Math.max(arr[i], head[i - 1] + arr[i]);
            tail[n - 1 - i] = i == 0 ? arr[n - 1 - i] : Math.max(arr[n - 1 - i], tail[n - i] + arr[n - 1 - i]);
            ret = Math.max(ret, Math.max(head[i], tail[n - 1 - i]));
        }
        for (int i = 1; i < n - 1; i++) {
            ret = Math.max(ret, head[i - 1] + tail[i + 1]);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarraySumWithOneDeletion().maximumSum(new int[]{2, 1, -2, -5, -2}));
        System.out.println(new MaximumSubarraySumWithOneDeletion().maximumSum(new int[]{1, -2, -2, 3}));
        System.out.println(new MaximumSubarraySumWithOneDeletion().maximumSum(new int[]{1, -2, 0, 3}));
    }
}
