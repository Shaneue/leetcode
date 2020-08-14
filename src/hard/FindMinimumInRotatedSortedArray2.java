package hard;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 * <p>
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinimumInRotatedSortedArray2 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else if (nums[m] == nums[r]) {
                r -= 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{1, 3, 5}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{3, 1, 3, 3}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{1, 2, 3}));
    }
}
