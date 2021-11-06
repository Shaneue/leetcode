package contest.hard;

/**
 * You are given an integer array nums. We call a subset of nums good if its product can be represented as a product of one or more distinct prime numbers.
 *
 * For example, if nums = [1, 2, 3, 4]:
 * [2, 3], [1, 2, 3], and [1, 3] are good subsets with products 6 = 2*3, 6 = 2*3, and 3 = 3 respectively.
 * [1, 4] and [4] are not good subsets with products 4 = 2*2 and 4 = 2*2 respectively.
 * Return the number of different good subsets in nums modulo 109 + 7.
 *
 * A subset of nums is any array that can be obtained by deleting some (possibly none or all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 6
 * Explanation: The good subsets are:
 * - [1,2]: product is 2, which is the product of distinct prime 2.
 * - [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [1,3]: product is 3, which is the product of distinct prime 3.
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * Example 2:
 *
 * Input: nums = [4,2,3,15]
 * Output: 5
 * Explanation: The good subsets are:
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [2,15]: product is 30, which is the product of distinct primes 2, 3, and 5.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * - [15]: product is 15, which is the product of distinct primes 3 and 5.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 30
 */
public class TheNumberOfGoodSubsets {
    public int numberOfGoodSubsets(int[] nums) {
        int[] map = new int[31];
        map[2] = 1;
        map[3] = 2;
        map[5] = 4;
        map[6] = 3;
        map[7] = 8;
        map[10] = 5;
        map[11] = 16;
        map[13] = 32;
        map[14] = 9;
        map[15] = 6;
        map[17] = 64;
        map[19] = 128;
        map[21] = 10;
        map[22] = 17;
        map[23] = 256;
        map[26] = 33;
        map[29] = 512;
        map[30] = 7;
        long[] dp = new long[1024];
        dp[0] = 1;
        int mod = (int) (1e9 + 7);
        int[] c = new int[31];
        long ret = 0;
        for (int i : nums) c[i]++;
        for (int i = 0; i < 31; i++) {
            if (map[i] != 0 && c[i] > 0) {
                for (int j = 0; j + map[i] < 1024; j++) {
                    if ((j | map[i]) == j + map[i]) {
                        dp[j + map[i]] = (dp[j] * c[i] + dp[j + map[i]]) % mod;
                    }
                }
            }
        }
        for (int i = 1; i < 1024; i++) {
            ret += dp[i];
        }
        return (int) ((ret % mod) * quickPower(2, c[1], mod) % mod);
    }

    long quickPower(long a, long b, long mod) {
        long ret = 1;
        while (b != 0) {
            if ((b & 1) == 1) ret = ret * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return (int) (ret % mod);
    }

    public static void main(String[] args) {
        System.out.println(new TheNumberOfGoodSubsets().numberOfGoodSubsets(new int[]{9, 3, 14, 12, 14, 3, 23, 23, 30, 9, 2, 6, 26, 17, 5, 8, 23, 6, 8, 9, 2, 4, 30, 21, 19, 8, 1, 23, 22, 26, 17, 20, 5, 15, 18, 20, 22, 2, 15, 8, 21, 9, 20}));
        System.out.println(new TheNumberOfGoodSubsets().numberOfGoodSubsets(new int[]{6, 8, 1, 8, 6, 5, 6, 11, 17}));
        System.out.println(new TheNumberOfGoodSubsets().numberOfGoodSubsets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}));
        System.out.println(new TheNumberOfGoodSubsets().numberOfGoodSubsets(new int[]{5, 10, 1, 26, 24, 21, 24, 23, 11, 12, 27, 4, 17, 16, 2, 6, 1, 1, 6, 8, 13, 30, 24, 20, 2, 19}));
        System.out.println(new TheNumberOfGoodSubsets().numberOfGoodSubsets(new int[]{18, 28, 2, 17, 29, 30, 15, 9, 12}));
    }
}
