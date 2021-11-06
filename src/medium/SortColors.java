package medium;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Follow up:
 *
 * Could you solve this problem without using the library's sort function?
 * Could you come up with a one-pass algorithm using only O(1) constant space?
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Example 3:
 *
 * Input: nums = [0]
 * Output: [0]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is 0, 1, or 2.
 *
 * 用交换的方法只需要遍历一遍
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int p0 = 0, p1 = 0, p2 = nums.length - 1;
        while (p0 < p2) {
            if (nums[p0] == 0) {
                nums[p0] = nums[p1];
                nums[p1] = 0;
                p1++;
            } else if (nums[p0] == 2) {
                nums[p0] = nums[p2];
                nums[p2] = 2;
                p2--;
                p0--;
            }
            p0++;
        }
    }

    public void sortColors2(int[] nums) {
        int zero = 0, one = 0, two = 0;
        for (int i : nums) {
            if (i == 1) one++;
            else if (i == 2) two++;
            else nums[zero++] = 0;
        }
        while (one > 0) {
            nums[zero++] = 1;
            one--;
        }
        while (two > 0) {
            nums[zero++] = 2;
            two--;
        }
    }

    public static void main(String[] args) {
        SortColors s = new SortColors();
        int[] a = new int[]{2, 0, 1};
        s.sortColors(a);
        System.out.println();
    }
}
