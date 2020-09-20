package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {

    public TimeBasedKeyValueStore() {

    }

    static class Pair {
        String val;
        int timestamp;

        public Pair(String val, int timestamp) {
            this.val = val;
            this.timestamp = timestamp;
        }
    }

    Map<String, List<Pair>> map = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        List<Pair> l = map.getOrDefault(key, new ArrayList<>());
        l.add(new Pair(value, timestamp));
        map.put(key, l);
    }

    public String get(String key, int timestamp) {
        List<Pair> list = map.get(key);
        if (list == null) return "";
        int l = 0, r = list.size() - 1;
        if (timestamp < list.get(l).timestamp) return "";
        while (l <= r) {
            int m = (l + r) / 2;
            Pair p = list.get(m);
            if (p.timestamp > timestamp) {
                r = m - 1;
            } else if (p.timestamp < timestamp) {
                l = m + 1;
            } else {
                return p.val;
            }
        }
        return list.get(r).val;
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore t = new TimeBasedKeyValueStore();
        t.set("foo", "bar", 1);
        System.out.println(t.get("foo", 1));
        System.out.println(t.get("foo", 3));
        t.set("foo", "bar2", 4);
        System.out.println(t.get("foo", 4));
        System.out.println(t.get("foo", 5));
    }
}
