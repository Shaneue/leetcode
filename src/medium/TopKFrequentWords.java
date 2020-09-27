package medium;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 *
 * 堆排序也是很高效的
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String s : words) {
            int i = count.getOrDefault(s, 0);
            count.put(s, i + 1);
        }
        TreeSet<String>[] bucket = new TreeSet[words.length + 1];
        for (int i = 0; i < bucket.length; i++) bucket[i] = new TreeSet<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            bucket[entry.getValue()].add(entry.getKey());
        }
        List<String> ret = new ArrayList<>();
        for (int i = words.length; i >= 0; i--) {
            if (k >= bucket[i].size()) {
                ret.addAll(bucket[i]);
                k -= bucket[i].size();
            } else {
                Iterator<String> it = bucket[i].iterator();
                while (k-- != 0) {
                    ret.add(it.next());
                }
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TopKFrequentWords t = new TopKFrequentWords();
        System.out.println(t.topKFrequent(new String[]{"the"}, 1));
        System.out.println(t.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}
