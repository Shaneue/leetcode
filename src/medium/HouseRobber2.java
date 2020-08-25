package medium;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * 分别去除首尾，计算两遍dp，取最大值
 */
public class HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length - 1; i++) {
            dp[i] = Math.max((i - 2 < 0 ? 0 : dp[i - 2]) + nums[i], dp[i - 1]);
        }
        int left = dp[dp.length - 2];
        dp[dp.length - 1] = nums[dp.length - 1];
        for (int i = dp.length - 2; i > 0; i--) {
            dp[i] = Math.max((i + 2 >= dp.length ? 0 : dp[i + 2]) + nums[i], dp[i + 1]);
        }
        return Math.max(left, dp[1]);
    }

    public static void main(String[] args) {
        HouseRobber2 h = new HouseRobber2();
        System.out.println(h.rob(new int[]{2, 3, 2}));
        System.out.println(h.rob(new int[]{1, 2, 3, 1}));
    }
}
