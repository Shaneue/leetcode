package contest.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 *
 * Implement the Fancy class:
 *
 * Fancy() Initializes the object with an empty sequence.
 * void append(val) Appends an integer val to the end of the sequence.
 * void addAll(inc) Increments all existing values in the sequence by an integer inc.
 * void multAll(m) Multiplies all existing values in the sequence by an integer m.
 * int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
 *
 *
 * Example 1:
 *
 * Input
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
 * Output
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 *
 * Explanation
 * Fancy fancy = new Fancy();
 * fancy.append(2);   // fancy sequence: [2]
 * fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
 * fancy.append(7);   // fancy sequence: [5, 7]
 * fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // return 10
 * fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
 * fancy.append(10);  // fancy sequence: [13, 17, 10]
 * fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // return 26
 * fancy.getIndex(1); // return 34
 * fancy.getIndex(2); // return 20
 *
 *
 * Constraints:
 *
 * 1 <= val, inc, m <= 100
 * 0 <= idx <= 10^5
 * At most 10^5 calls total will be made to append, addAll, multAll, and getIndex.
 *
 * 快速幂求逆元
 */
public class FancySequence {
    static class Fancy {
        int mod = (int) (1e9 + 7);
        List<Long> list = new ArrayList<>();
        List<Long> add = new ArrayList<>();
        List<Long> mul = new ArrayList<>();

        public Fancy() {
            add.add((long) 0);
            mul.add((long) 1);
        }

        public void append(int val) {
            list.add((long) val);
            add.add(add.get(add.size() - 1));
            mul.add(mul.get(mul.size() - 1));
        }

        public void addAll(int inc) {
            add.set(add.size() - 1, (add.get(add.size() - 1) + inc) % mod);
        }

        public void multAll(int m) {
            add.set(add.size() - 1, (add.get(add.size() - 1) * m) % mod);
            mul.set(mul.size() - 1, (mul.get(mul.size() - 1) * m) % mod);
        }

        public int getIndex(int idx) {
            if (idx >= list.size()) return -1;
            long ret = list.get(idx);
            long m = mul.get(mul.size() - 1) * inverseElement(mul.get(idx), mod) % mod;
            ret = ret * m % mod;
            ret = ((ret + add.get(add.size() - 1) - add.get(idx) * m) % mod + mod) % mod;//负数
            return (int) ret;
        }

        long inverseElement(long a, long n) {
            return quickPower(a, n - 2, n);
        }

        long quickPower(long a, long b, long mod) {
            long ret = 1;
            while (b != 0) {
                if ((b & 1) == 1) ret = ret * a % mod;
                a = a * a % mod;
                b >>= 1;
            }
            return (int) (ret % mod);
        }
    }

    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        System.out.println(fancy.getIndex(0));
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        System.out.println(fancy.getIndex(0));
        System.out.println(fancy.getIndex(1));
        System.out.println(fancy.getIndex(2));
    }
}
