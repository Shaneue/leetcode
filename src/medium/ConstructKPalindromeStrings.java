package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 * <p>
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 * Example 2:
 * <p>
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 * Example 3:
 * <p>
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 * Example 4:
 * <p>
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 * Example 5:
 * <p>
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * All characters in s are lower-case English letters.
 * 1 <= k <= 10^5
 * <p>
 * 用HashSet有点过慢，可以直接用数据来统计字母个数。
 */
public class ConstructKPalindromeStrings {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= k;
    }
}
