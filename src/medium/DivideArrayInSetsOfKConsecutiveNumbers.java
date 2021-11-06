package medium;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 * <p>
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * <p>
 * 从最小值开始，减去的时候要从当前consecutive numbers的最后一个数减起
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] A, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : A) map.put(i, map.getOrDefault(i, 0) + 1);
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                for (int i = k - 1; i >= 0; i--) {
                    if (map.getOrDefault(key + i, 0) < map.get(key)) return false;
                    map.put(key + i, map.get(key + i) - map.get(key));
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DivideArrayInSetsOfKConsecutiveNumbers d = new DivideArrayInSetsOfKConsecutiveNumbers();
        System.out.println(d.isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(d.isPossibleDivide(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(d.isPossibleDivide(new int[]{3, 3, 2, 2, 1, 1}, 3));
    }
}
