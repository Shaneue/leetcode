package contest.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        return dfs(s, new HashSet<>());
    }

    int dfs(String s, Set<String> current) {
        int ret = 0;
        for (int i = 1; i <= s.length(); i++) {
            String t = s.substring(0, i);
            if (!current.contains(t)) {
                current.add(t);
                ret = Math.max(ret, 1 + dfs(s.substring(i), current));
                current.remove(t);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        SplitAStringIntoTheMaxNumberOfUniqueSubstrings s = new SplitAStringIntoTheMaxNumberOfUniqueSubstrings();
        System.out.println(s.maxUniqueSplit("fcmfacp"));
        System.out.println(s.maxUniqueSplit("hmadataa"));
        System.out.println(s.maxUniqueSplit("gahbag"));
        System.out.println(s.maxUniqueSplit("aa"));
        System.out.println(s.maxUniqueSplit("aba"));
    }
}
