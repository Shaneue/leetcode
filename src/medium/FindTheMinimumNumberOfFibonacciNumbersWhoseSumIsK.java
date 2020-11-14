package medium;

/**
 * Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. The same Fibonacci number can be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 for n > 2.
 * It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 *
 *
 * Example 1:
 *
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * Example 2:
 *
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 *
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 *
 * 可以证明，每次取最大值离k最近的那个值即可得最优解
 */
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {

    public int findMinFibonacciNumbers(int k) {
        int a = 0, b = 1;
        while (b <= k) {
            b += a;
            a = b - a;
        }
        return 1 + (k - a == 0 ? 0 : findMinFibonacciNumbers(k - a));
    }

    public static void main(String[] args) {
        FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK f = new FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK();
        System.out.println(f.findMinFibonacciNumbers(19));
    }
}
