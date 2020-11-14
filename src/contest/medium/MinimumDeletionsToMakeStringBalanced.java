package contest.medium;

/**
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 *
 * You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 *
 * Return the minimum number of deletions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * Example 2:
 *
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is 'a' or 'b'​​.
 */
public class MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int ret = 0;
        for (char ch : chars) {
            if (ch == 'b') count++;
            else ret++;
            ret = Math.min(ret, count);
        }
        return ret;
    }
}
