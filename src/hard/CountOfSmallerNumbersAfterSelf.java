package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int[] f = new int[20001];
        List<Integer> ret = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            ret.add(query(f, nums[i] + 10000 - 1));
            update(f, nums[i] + 10000);
        }
        Collections.reverse(ret);
        return ret;
    }

    void update(int[] f, int x) {
        for (; x < f.length; x += x & -x) {
            f[x] += 1;
        }
    }

    int query(int[] f, int x) {
        int sum = 0;
        for (; x > 0; x -= x & -x) {
            sum += f[x];
        }
        return sum;
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        System.out.println(c.countSmaller(new int[]{5, 2, 6, 1}));
    }
}
