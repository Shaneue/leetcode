package contest.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string word that consists of digits and lowercase English letters.
 *
 * You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".
 *
 * Return the number of different integers after performing the replacement operations on word.
 *
 * Two integers are considered different if their decimal representations without any leading zeros are different.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "a123bc34d8ef34"
 * Output: 3
 * Explanation: The three different integers are "123", "34", and "8". Notice that "34" is only counted once.
 * Example 2:
 *
 * Input: word = "leet1234code234"
 * Output: 2
 * Example 3:
 *
 * Input: word = "a1b01c001"
 * Output: 1
 * Explanation: The three integers "1", "01", and "001" all represent the same integer because
 * the leading zeros are ignored when comparing their decimal values.
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 1000
 * word consists of digits and lowercase English letters.
 */
public class NumberOfDifferentIntegersInAString {
    public int numDifferentIntegers(String word) {
        char[] chars = word.toCharArray();
        Set<String> ret = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                if (sb.length() != 0) {
                    ret.add(removeLeadingZeros(sb));
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
            ret.add(removeLeadingZeros(sb));
        }
        return ret.size();
    }

    private String removeLeadingZeros(StringBuilder sb) {
        int i = 0;
        for (; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') break;
        }
        return i == sb.length() ? "0" : sb.substring(i);
    }
}
