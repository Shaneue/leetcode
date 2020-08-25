package easy;

/**
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 *
 * 两个方向遍历一边，取最小
 */
public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        int[] fromLeftToRight = new int[S.length()];
        int[] ret = new int[S.length()];
        int t = -100000;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != C) {
                fromLeftToRight[i] = i - t;
            } else {
                fromLeftToRight[i] = 0;
                t = i;
            }
        }
        t = 100000;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) != C) {
                ret[i] = Math.min(t - i, fromLeftToRight[i]);
            } else {
                ret[i] = 0;
                t = i;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        ShortestDistanceToACharacter s = new ShortestDistanceToACharacter();
        int[] r = s.shortestToChar("loveleetcode", 'e');
        System.out.println();
    }
}
