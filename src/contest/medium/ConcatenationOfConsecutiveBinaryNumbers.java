package contest.medium;

public class ConcatenationOfConsecutiveBinaryNumbers {
    static long[] ret = new long[100001];

    static {
        int mod = (int) (1e9 + 7);
        for (int i = 1; i <= 100000; i++) {
            int l = Integer.toString(i, 2).length();
            ret[i] = (long) ((ret[i - 1] * Math.pow(2, l) + i) % mod);
        }
    }

    public int concatenatedBinary(int n) {
        return (int) ret[n];
    }

    public static void main(String[] args) {
        ConcatenationOfConsecutiveBinaryNumbers c = new ConcatenationOfConsecutiveBinaryNumbers();
        long start = System.currentTimeMillis();
        for (int i = 100000; i >= 99900; i--)
            c.concatenatedBinary(i);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(c.concatenatedBinary(12));
        System.out.println(c.concatenatedBinary(3));
    }
}
