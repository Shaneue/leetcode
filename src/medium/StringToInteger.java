package medium;


/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 */
public class StringToInteger {
    public int myAtoi(String str) {
        long ret = 0;
        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ')
            start++;
        if (start >= str.length())
            return 0;
        int sign = 1;
        if (str.charAt(start) == '-') {
            sign = -1;
            start++;
        } else if (str.charAt(start) == '+') {
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                break;
            ret = ret * 10 + str.charAt(i) - '0';
            if (ret * sign > Integer.MAX_VALUE) {
                ret = Integer.MAX_VALUE;
                break;
            }
            if (ret * sign < Integer.MIN_VALUE) {
                ret = Integer.MIN_VALUE;
                break;
            }
        }
        return (int) ret * sign;
    }
}
