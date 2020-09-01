package medium;

import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 *
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 *
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 *
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 *
 * 先排序，然后递归
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) return false;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % 4 != 0) return false;
        int sideLength = sum / 4;
        int c = 0;
        for (int j = nums.length - 1; j >= 0; j--) {
            if (!used[j]) {
                if (!match(nums, used, 0, j, sideLength)) return false;
                else c++;
            }
        }
        return c == 4;
    }

    boolean match(int[] nums, boolean[] used, int c, int k, int target) {
        if (c == target) return true;
        if (c > target) return false;
        for (int i = k; i >= 0; i--) {
            if (!used[i]) {
                used[i] = true;
                if (match(nums, used, c + nums[i], i - 1, target)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MatchsticksToSquare m = new MatchsticksToSquare();
        System.out.println(m.makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
        System.out.println(m.makesquare(new int[]{3, 3, 3, 3, 4}));
        System.out.println(m.makesquare(new int[]{1, 1, 2, 2, 2}));
    }
}
