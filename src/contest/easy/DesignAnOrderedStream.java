package contest.easy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There are n (id, value) pairs, where id is an integer between 1 and n and value is a string. No two pairs have the same id.
 *
 * Design a stream that takes the n pairs in an arbitrary order, and returns the values over several calls in increasing order of their ids.
 *
 * Implement the OrderedStream class:
 *
 * OrderedStream(int n) Constructs the stream to take n values and sets a current ptr to 1.
 * String[] insert(int id, String value) Stores the new (id, value) pair in the stream. After storing the pair:
 * If the stream has stored a pair with id = ptr, then find the longest contiguous incrementing sequence of ids starting with id = ptr and return a list of the values associated with those ids in order. Then, update ptr to the last id + 1.
 * Otherwise, return an empty list.
 *
 *
 * Example:
 *
 *
 *
 * Input
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * Output
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 *
 * Explanation
 * OrderedStream os= new OrderedStream(5);
 * os.insert(3, "ccccc"); // Inserts (3, "ccccc"), returns [].
 * os.insert(1, "aaaaa"); // Inserts (1, "aaaaa"), returns ["aaaaa"].
 * os.insert(2, "bbbbb"); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
 * os.insert(5, "eeeee"); // Inserts (5, "eeeee"), returns [].
 * os.insert(4, "ddddd"); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value consists only of lowercase letters.
 * Each call to insert will have a unique id.
 * Exactly n calls will be made to insert.
 */
public class DesignAnOrderedStream {
    String[] stream;
    int p;
    int start;

    public DesignAnOrderedStream(int n) {
        stream = new String[n];
        p = 0;
        start = 0;
    }

    public List<String> insert(int id, String value) {
        stream[id - 1] = value;
        while (p < stream.length && stream[p] != null) p++;
        int t = start;
        start = p;
        return Arrays.stream(stream).collect(Collectors.toList()).subList(t, p);
    }

    public static void main(String[] args) {
        DesignAnOrderedStream d = new DesignAnOrderedStream(5);
        System.out.println(d.insert(3, "cc"));
        System.out.println(d.insert(1, "aa"));
        System.out.println(d.insert(2, "bb"));
        System.out.println(d.insert(5, "ee"));
        System.out.println(d.insert(4, "dd"));
    }
}
