package hard;

import java.util.*;

/**
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i] <= 300
 * words[i] consists of lower-case English letters.
 *
 * 两个串互为reverse时要特殊处理一下
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            for (int j = words[i].length(); j >= 0; j--) {
                if (isPalindrome(words[i].substring(j))) {
                    String sub = new StringBuilder(words[i].substring(0, j)).reverse().toString();
                    if (map.containsKey(sub)) {
                        int k = map.get(sub);
                        if (k != i)
                            ret.add(Arrays.asList(i, k));
                    }
                }
                if (isPalindrome(words[i].substring(0, words[i].length() - j))) {
                    if (j == words[i].length()) continue; // 去重
                    String sub = new StringBuilder(words[i].substring(words[i].length() - j)).reverse().toString();
                    if (map.containsKey(sub)) {
                        int k = map.get(sub);
                        if (k != i)
                            ret.add(Arrays.asList(k, i));
                    }
                }
            }
        }
        return ret;
    }

    boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j)) return false;
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs p = new PalindromePairs();
        System.out.println(p.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(p.palindromePairs(new String[]{"a", "abc", "aba", ""}));
        System.out.println(p.palindromePairs(new String[]{"a", ""}));
        System.out.println(p.palindromePairs(new String[]{"bat", "tab", "cat"}));
    }
}
