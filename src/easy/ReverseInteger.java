package easy;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 */
public class ReverseInteger {
    public int reverse(int x) {
        boolean sign = x < 0;
        long xl = x;
        xl = Math.abs(xl);
        long r = 0;
        while (xl > 0) {
            r = r * 10 + xl % 10;
            xl /= 10;
        }
        if (sign) {
            r *= -1;
        }
        return r < Integer.MIN_VALUE || r > Integer.MAX_VALUE ? 0 : (int) r;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(15342364));
    }
}
