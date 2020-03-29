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
        boolean flag = total % 2 == 1;
        int count = 0;
        double re = 0.0;
        if (flag) {
            for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
                if (i == nums1.length) {
                    count++;
                    if (count == target + 1) {
                        re = 0.0 + nums2[j];
                        break;
                    }
                    j++;
                } else if (j == nums2.length) {
                    count++;
                    if (count == target + 1) {
                        re = 0.0 + nums1[i];
                        break;
                    }
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    count++;
                    if (count == target + 1) {
                        re = 0.0 + nums2[j];
                        break;
                    }
                    j++;
                } else {
                    count++;
                    if (count == target + 1) {
                        re = 0.0 + nums1[i];
                        break;
                    }
                    i++;
                }
            }
        } else {
            for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
                if (i == nums1.length) {
                    count++;
                    if (count == target) {
                        re = 0.0 + nums2[j];
                    }
                    if (count == target + 1) {
                        re = re + nums2[j];
                        re /= 2;
                        break;
                    }
                    j++;
                } else if (j == nums2.length) {
                    count++;
                    if (count == target) {
                        re = 0.0 + nums1[i];
                    }
                    if (count == target + 1) {
                        re = re + nums1[i];
                        re /= 2;
                        break;
                    }
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    count++;
                    if (count == target) {
                        re = 0.0 + nums2[j];
                    }
                    if (count == target + 1) {
                        re = re + nums2[j];
                        re /= 2;
                        break;
                    }
                    j++;
                } else {
                    count++;
                    if (count == target) {
                        re = 0.0 + nums1[i];
                    }
                    if (count == target + 1) {
                        re = re + nums1[i];
                        re /= 2;
                        break;
                    }
                    i++;
                }
            }
        }
        return re;
    }
}
