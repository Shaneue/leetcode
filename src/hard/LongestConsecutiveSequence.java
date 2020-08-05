package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ret = 0;
        for (int i : nums) {
            set.add(i);
        }
        for (int i : nums) {
            int count = 0;
            int t = i;
            while (set.contains(t)) {
                count++;
                set.remove(t);
                t++;
            }
            t = i - 1;
            while (set.contains(t)) {
                count++;
                set.remove(t);
                t--;
            }
            if (count > ret) ret = count;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] a = {100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(a));
    }
}
