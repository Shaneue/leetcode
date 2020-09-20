package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * 滑动窗口
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) return new ArrayList<>();
        int[] h = new int[26];
        for (char c : p.toCharArray()) h[c - 'a']++;
        int l = 0, r = 0, count = 0;
        List<Integer> ret = new ArrayList<>();
        while (r < s.length()) {
            if (h[s.charAt(r++) - 'a']-- > 0) count++;
            if (count == p.length()) ret.add(l);
            // ++h[s.charAt(l++) - 'a'] > 0 说明l的位置属于anagram里的一个元素
            if (r - l == p.length() && ++h[s.charAt(l++) - 'a'] > 0) count--;
        }
        return ret;
    }

    public static void main(String[] args) {
        FindAllAnagramsInAString f = new FindAllAnagramsInAString();
        System.out.println(f.findAnagrams("abab", "ab"));
    }
}
