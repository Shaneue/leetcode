package easy;

/**
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 234
 * Output: 15
 * Explanation:
 * Product of digits = 2 * 3 * 4 = 24
 * Sum of digits = 2 + 3 + 4 = 9
 * Result = 24 - 9 = 15
 * Example 2:
 * <p>
 * Input: n = 4421
 * Output: 21
 * Explanation:
 * Product of digits = 4 * 4 * 2 * 1 = 32
 * Sum of digits = 4 + 4 + 2 + 1 = 11
 * Result = 32 - 11 = 21
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        for (int i = n; i > 0; ) {
            int remainder = i % 10;
            product *= remainder;
            sum += remainder;
            i /= 10;
        }
        return product - sum;
    }

    public static void main(String[] args) {
        System.out.println(new SubtractTheProductAndSumOfDigitsOfAnInteger().subtractProductAndSum(234));
        System.out.println(new SubtractTheProductAndSumOfDigitsOfAnInteger().subtractProductAndSum(4401));
    }
}
