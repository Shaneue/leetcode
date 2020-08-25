package easy;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 *
 * 可以化简一下代码，只用一个迭代即可
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder longer, shorter;
        if (a.length() > b.length()) {
            longer = new StringBuilder(a).reverse();
            shorter = new StringBuilder(b).reverse();
        } else {
            longer = new StringBuilder(b).reverse();
            shorter = new StringBuilder(a).reverse();
        }
        int c = 0;
        int i = 0;
        for (; i < shorter.length(); i++) {
            int v = shorter.charAt(i) - '0' + longer.charAt(i) - '0' + c;
            if (v > 1) {
                v -= 2;
                c = 1;
            } else {
                c = 0;
            }
            sb.append(v);
        }

        for (; i < longer.length(); i++) {
            int v = longer.charAt(i) - '0' + c;
            if (v > 1) {
                v -= 2;
                c = 1;
            } else {
                c = 0;
            }
            sb.append(v);
        }
        if (c == 1) sb.append(c);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary a = new AddBinary();
        System.out.println(a.addBinary("1", "1"));
        System.out.println(a.addBinary("1010", "1011"));
    }
}
