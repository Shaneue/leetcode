package medium;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums is a non-decreasing array.
 * -10^9 <= target <= 10^9
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        if (nums.length == 0) return ret;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[l] != target) return ret;
        else ret[0] = l;
        r = nums.length - 1; // no need to reset l
        while (l < r) {
            int mid = l + (r - l) / 2 + 1; // important
            if (nums[mid] > target) r = mid - 1;
            else l = mid;
        }
        ret[1] = l;
        return ret;
    }

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray f = new FindFirstAndLastPositionOfElementInSortedArray();
        System.out.println(Arrays.toString(f.searchRange(new int[]{5}, 6)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{5}, 5)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
    }
}
