package contest.easy;

/**
 * Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters into lower case letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.
 *
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 *
 * Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, return any of them. It can be shown that an answer is always possible with the given constraints.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid. Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
 * Example 2:
 *
 * Input: s = "ubv?w"
 * Output: "ubvaw"
 * Explanation: There are 24 solutions for this problem. Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
 * Example 3:
 *
 * Input: s = "j?qg??b"
 * Output: "jaqgacb"
 * Example 4:
 *
 * Input: s = "??yw?ipkj?"
 * Output: "acywaipkja"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 *
 * s contains only lower case English letters and '?'.
 */
public class ReplaceAllToAvoidConsecutiveRepeatingCharacters {
    public String modifyString(String s) {
        if (s.length() == 1) {
            if (s.charAt(0) == '?') return "a";
            else return s;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '?') {
                char t = 'a';
                for (char c = 'a'; c < 'z'; c++) {
                    if (i == 0) {
                        if (c != sb.charAt(i + 1)) {
                            t = c;
                            break;
                        }
                    } else if (i == sb.length() - 1) {
                        if (sb.charAt(i - 1) != c) {
                            t = c;
                            break;
                        }
                    } else {
                        if (sb.charAt(i - 1) != c && sb.charAt(i + 1) != c) {
                            t = c;
                            break;
                        }
                    }
                }
                sb.setCharAt(i, t);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReplaceAllToAvoidConsecutiveRepeatingCharacters r = new ReplaceAllToAvoidConsecutiveRepeatingCharacters();
        System.out.println(r.modifyString("?a?ub???w?b"));
    }
}
