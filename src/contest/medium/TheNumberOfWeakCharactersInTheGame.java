package contest.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 *
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 *
 * Return the number of weak characters.
 *
 *
 *
 * Example 1:
 *
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * Example 2:
 *
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * Example 3:
 *
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *
 *
 * Constraints:
 *
 * 2 <= properties.length <= 10^5
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 10^5
 */
public class TheNumberOfWeakCharactersInTheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, Comparator.comparingInt(a -> a[0]));
        int lastMax = Integer.MIN_VALUE;
        int ret = 0;
        for (int i = properties.length - 1; i >= 0; i--) {
            int currentMax = properties[i][1];
            while (i - 1 >= 0 && properties[i - 1][0] == properties[i][0]) {
                if (properties[i][1] < lastMax) ret++;
                i--;
                currentMax = Math.max(currentMax, properties[i][1]);
            }
            if (properties[i][1] < lastMax) ret++;
            lastMax = Math.max(currentMax, lastMax);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new TheNumberOfWeakCharactersInTheGame().numberOfWeakCharacters(new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}}));
    }
}
