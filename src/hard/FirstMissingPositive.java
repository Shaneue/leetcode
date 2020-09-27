package hard;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * Follow up:
 * Your algorithm should run in O(n) time and uses constant extra space.
 *
 * 注意条件nums[i] != nums[nums[i] - 1]
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            while (nums[i] > 0 && nums[i] <= l && nums[i] != nums[nums[i] - 1]) {
                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            }
        }
        for (int i = 0; i < l; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return l + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive f = new FirstMissingPositive();
        System.out.println(f.firstMissingPositive(new int[]{1, 1}));
        System.out.println(f.firstMissingPositive(new int[]{1, 2}));
        System.out.println(f.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(f.firstMissingPositive(new int[]{7, 8, 9}));
    }
}
