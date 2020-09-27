package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * Absolute value of elements in the array and x will not exceed 10^4
 *
 * 最优解是二分搜索
 * (arr[m + k] - x) - (x - arr[m])表示尽量靠着答案的最左边下标走，因为答案一定是连续的k个元素，是整个区间的二分搜索
 * >=表示答案区间应该往左边移
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length - k;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m + k] - x >= x - arr[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return Arrays.stream(arr, l, l + k).boxed().collect(Collectors.toList());
    }

    public List<Integer> findClosestElementsBucket(int[] arr, int k, int x) {
        List<Integer>[] bucket = new ArrayList[20001];
        for (int i = 0; i < 20001; i++) bucket[i] = new ArrayList<>();
        for (int i : arr) bucket[Math.abs(i - x)].add(i);
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 20001; i++) {
            if (bucket[i].size() <= k) {
                ret.addAll(bucket[i]);
                k -= bucket[i].size();
            } else {
                for (int j = 0; j < k; j++) ret.add(bucket[i].get(j));
                break;
            }
        }
        Collections.sort(ret);
        return ret;
    }

    public static void main(String[] args) {
        FindKClosestElements f = new FindKClosestElements();
        System.out.println(f.findClosestElements(new int[]{1}, 1, -1));
        System.out.println(f.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1));
    }
}
