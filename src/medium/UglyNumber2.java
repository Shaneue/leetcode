package medium;

/**
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 * <p>
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        int[] array = new int[n];
        array[0] = 1;
        int p2, p3, p5;
        p2 = p3 = p5 = 0;
        for (int i = 1; i < n; i++) {
            int a = array[p2] * 2;
            int b = array[p3] * 3;
            int c = array[p5] * 5;
            array[i] = Math.min(a, Math.min(b, c));
            if (a == array[i]) p2++;
            if (b == array[i]) p3++;
            if (c == array[i]) p5++;
        }
        return array[n - 1];
    }

    public static void main(String[] args) {
        UglyNumber2 u = new UglyNumber2();
        System.out.println(u.nthUglyNumber(10));
    }

    public int nthUglyNumberList(int n) {
        Node list = new Node(1);
        Node n1, n2, n3;
        n1 = n2 = n3 = list;
        for (int i = 1; i < n; i++) {
            int a = n1.val * 2;
            int b = n2.val * 3;
            int c = n3.val * 5;
            int next = Math.min(a, Math.min(b, c));
            list.next = new Node(next);
            list = list.next;
            if (a == next) {
                n1 = n1.next;
            }
            if (b == next) {
                n2 = n2.next;
            }
            if (c == next) {
                n3 = n3.next;
            }
        }
        return list.val;
    }

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
