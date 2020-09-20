package medium;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 *
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 *
 * Constraints:
 *
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 * dp[j]+=dp[j-i] for i in nums for j from target to i
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) sum += i;
        if ((sum + S) % 2 == 1 || sum < S) return 0;
        int target = (sum + S) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i : nums) {
            for (int j = target; j >= i; j--) {
                dp[j] += dp[j - i];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        TargetSum t = new TargetSum();
        System.out.println(t.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
