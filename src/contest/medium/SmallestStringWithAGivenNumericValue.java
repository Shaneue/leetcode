package contest.medium;

/**
 * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
 *
 * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
 *
 * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 *
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 27
 * Output: "aay"
 * Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
 * Example 2:
 *
 * Input: n = 5, k = 73
 * Output: "aaszz"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * n <= k <= 26 * n
 */
public class SmallestStringWithAGivenNumericValue {
    public String getSmallestString(int n, int k) {
        int a = n - (k - n) / 25 - 1;
        int z = (k - n) / 25;
        int mid = k - z * 26 - a;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) sb.append('a');
        if (mid > 0) sb.append((char) ('a' + mid - 1));
        for (int i = 0; i < z; i++) sb.append('z');
        return sb.toString();
    }

    public static void main(String[] args) {
        SmallestStringWithAGivenNumericValue s = new SmallestStringWithAGivenNumericValue();
        System.out.println(s.getSmallestString(5, 73));
    }
}
