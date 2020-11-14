package contest.medium;

import java.util.Arrays;

/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 * Example 2:
 *
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 * Example 3:
 *
 * Input: inventory = [2,8,4,10,6], orders = 20
 * Output: 110
 * Example 4:
 *
 * Input: inventory = [1000000000], orders = 1000000000
 * Output: 21
 * Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.
 *
 *
 * Constraints:
 *
 * 1 <= inventory.length <= 10^5
 * 1 <= inventory[i] <= 10^9
 * 1 <= orders <= min(sum(inventory[i]), 10^9)
 *
 * 从order递减开始贪心递减，要考虑越界
 */
public class SellDiminishingValuedColoredBalls {
    public int maxProfit(int[] inventory, int orders) {
        long ret = 0;
        Arrays.sort(inventory);
        int n = inventory.length - 1;
        int c = 1;
        while (orders > 0) {
            if (n > 0 && inventory[n] - inventory[n - 1] > 0 && orders >= c * (inventory[n] - inventory[n - 1])) {
                ret += c * ((long) inventory[n] + inventory[n - 1] + 1) * (inventory[n] - inventory[n - 1]) / 2;
                orders -= c * ((long) inventory[n] - inventory[n - 1]);
            } else if (n == 0 || inventory[n] - inventory[n - 1] > 0) {
                int a = orders / c;
                ret += c * ((long) inventory[n] + inventory[n] - a + 1) * a / 2;
                int b = orders % c;
                ret += b * ((long) inventory[n] - a);
                orders = 0;
            }
            n--;
            c++;
        }
        return (int) (ret % (int) (1e9 + 7));
    }


    public static void main(String[] args) {
        SellDiminishingValuedColoredBalls s = new SellDiminishingValuedColoredBalls();
        System.out.println(s.maxProfit(new int[]{497978859, 167261111, 483575207, 591815159}, 836556809));
        System.out.println(s.maxProfit(new int[]{2, 8, 4, 10, 6}, 20));
    }
}
