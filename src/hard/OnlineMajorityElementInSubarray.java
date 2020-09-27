package hard;

import java.util.*;

/**
 * Implementing the class MajorityChecker, which has the following API:
 *
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
 * int query(int left, int right, int threshold) has arguments such that:
 * 0 <= left <= right < arr.length representing a subarray of arr;
 * 2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
 * Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if no such element exists.
 *
 *
 *
 * Example:
 *
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // returns 1
 * majorityChecker.query(0,3,3); // returns -1
 * majorityChecker.query(2,3,2); // returns 2
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 20000
 * 1 <= arr[i] <= 20000
 * For each query, 0 <= left <= right < len(arr)
 * For each query, 2 * threshold > right - left + 1
 * The number of queries is at most 10000
 *
 * 最优解是线段树
 */
public class OnlineMajorityElementInSubarray {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] A;

    public OnlineMajorityElementInSubarray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        this.A = arr;
    }

    public int query(int left, int right, int threshold) {
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int idx = left + random.nextInt(right - left + 1);
            List<Integer> list = map.get(A[idx]);
            int l = Collections.binarySearch(list, left);
            int r = Collections.binarySearch(list, right);
            if (l < 0) l = -l - 1;
            if (r < 0) r = -r - 2;
            if (r - l + 1 >= threshold) return A[idx];
        }
        return -1;
    }

    public static void main(String[] args) {
        OnlineMajorityElementInSubarray o = new OnlineMajorityElementInSubarray(new int[]{1, 1, 2, 2, 1, 1});
        System.out.println(o.query(0, 5, 4));
        System.out.println(o.query(0, 3, 3));
        System.out.println(o.query(2, 3, 2));
    }
}
