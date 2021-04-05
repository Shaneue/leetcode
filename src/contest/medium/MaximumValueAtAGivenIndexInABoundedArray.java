package contest.medium;

/**
 * You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
 * Example 2:
 *
 * Input: n = 6, index = 1,  maxSum = 10
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= maxSum <= 10^9
 * 0 <= index < n
 *
 * 可以让mid偏向right最后就不需要加判断了，因为计算的sum有可能取不到target值，这需要需要往左偏一点。
 */
public class MaximumValueAtAGivenIndexInABoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        int l = 0, r = maxSum - n;
        int target = r;
        while (l < r) {
            int mid = l + (r - l) / 2;
            long sum = sum(n, index, mid);
            if (sum >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return sum(n, index, l) > target ? l : l + 1;
    }

    private long sum(int n, int index, int mid) {
        long t = Math.max(0, mid - index);
        long left = (t + mid) * (mid - t + 1) / 2;
        t = Math.max(0, mid - n + index + 1);
        long right = (mid + t) * (mid - t + 1) / 2;
        return left + right - mid;
    }

    public static void main(String[] args) {
        MaximumValueAtAGivenIndexInABoundedArray m = new MaximumValueAtAGivenIndexInABoundedArray();
        System.out.println(m.maxValue(4, 2, 6));
        System.out.println(m.maxValue(6, 1, 10));
    }
}
