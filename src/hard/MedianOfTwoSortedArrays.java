package hard;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int target = total / 2;
        int count = 0;
        double ret1 = 0.0, ret2 = 0.0;
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
            if (i == nums1.length) {
                if (++count == target + 1) {
                    ret1 = nums2[j];
                    break;
                }
                if (count == target) {
                    ret2 = nums2[j];
                }
                j++;
            } else if (j == nums2.length) {
                if (++count == target + 1) {
                    ret1 = nums1[i];
                    break;
                }
                if (count == target) {
                    ret2 = nums1[i];
                }
                i++;
            } else if (nums1[i] > nums2[j]) {
                if (++count == target + 1) {
                    ret1 = nums2[j];
                    break;
                }
                if (count == target) {
                    ret2 = nums2[j];
                }
                j++;
            } else {
                if (++count == target + 1) {
                    ret1 = nums1[i];
                    break;
                }
                if (count == target) {
                    ret2 = nums1[i];
                }
                i++;
            }
        }
        return total % 2 == 1 ? ret1 : (ret1 + ret2) / 2;
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}
