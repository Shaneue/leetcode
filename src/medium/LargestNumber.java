package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * 注意要用long
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        List<String> strings = new ArrayList<>(nums.length);
        for (int num : nums) {
            strings.add(Integer.toString(num));
        }
        strings.sort((a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumber().largestNumber(new int[]{10, 2}));
    }
}
