package fun;

import java.util.Arrays;

/**
 * 很有技巧
 */
public class MedianOfTwoSortedArraysWithSameLength {
    public int findMedianInTwoSortedArray(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int l = 0, r = n - 1, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (arr1[m] < arr2[n - 1 - m]) l = m + 1;
            else r = m;
        }
        int[] ret = {arr1[l], l == 0 ? Integer.MIN_VALUE : arr1[l - 1], arr2[n - 1 - l], l == 0 ? Integer.MAX_VALUE : arr2[n - l]};
        Arrays.sort(ret);
        return ret[1];
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArraysWithSameLength m = new MedianOfTwoSortedArraysWithSameLength();
        System.out.println(m.findMedianInTwoSortedArray(new int[]{1}, new int[]{2}));
    }
}
