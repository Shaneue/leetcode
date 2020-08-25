package medium;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 * <p>
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int[] product = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int p = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int tp = p + product[i + j + 1];
                product[i + j + 1] = tp % 10;
                product[i + j] += tp / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : product) {
            if (sb.length() != 0 || i != 0)
                sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String test1 = new MultiplyStrings().multiply("1", "2");
        assert test1.equals("2");
        String test2 = new MultiplyStrings().multiply("1", "2222");
        assert test2.equals("2222");
        String test3 = new MultiplyStrings().multiply("10", "200");
        assert test3.equals("2000");
        String test4 = new MultiplyStrings().multiply("111", "111");
        assert test4.equals("12321");
        String test5 = new MultiplyStrings().multiply("123", "456");
        assert test5.equals("56088");
        String test6 = new MultiplyStrings().multiply("0", "0");
        assert test6.equals("0");
    }
}
