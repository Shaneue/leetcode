package hard;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * 千万不要用bfs，直接贪心往后面走。
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        int ret = 1;

        if (n == 1) {
            return 0;
        }

        int rightmost = nums[0];
        int endCurrent = nums[0];

        for (int i = 1; i < n; i++) {
            if (i == n - 1) {
                return ret;
            }
            rightmost = Math.max(rightmost, nums[i] + i);
            if (i == endCurrent) {
                ret++;
                endCurrent = rightmost;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame2().jump(new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));
        System.out.println(new JumpGame2().jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new JumpGame2().jump(new int[]{2}));
    }
}
