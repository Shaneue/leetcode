package hard;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * 注意dp时，left与right的选择，每个j的含义指的是j对应的元素在最后burst
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int l = nums.length + 2;
        int[] n = new int[l];
        n[0] = n[l - 1] = 1;
        System.arraycopy(nums, 0, n, 1, l - 2);
        int[][] dp = new int[l][l];
        for (int k = 0; k < l - 2; k++) {
            for (int i = 1; i < l - 1 - k; i++) {
                for (int j = i; j <= i + k; j++) {
                    dp[i][i + k] = Math.max(dp[i][i + k], dp[i][j - 1] + dp[j + 1][i + k] + n[i - 1] * n[j] * n[i + k + 1]);
                }
            }
        }
        return dp[1][l - 2];
    }

    public static void main(String[] args) {
        System.out.println(new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }
}
