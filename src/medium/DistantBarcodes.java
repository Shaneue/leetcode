package medium;

import java.util.*;

/**
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * <p>
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 * Example 2:
 * <p>
 * Input: [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,2,1,2,1]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 *
 *
 * 最快的方法是从左向右遍历，消除相邻且相同元素。这一趟消除不完全，需要再从右向左消除一次才能达到目的。
 */
public class DistantBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes.length == 1 || barcodes.length == 2 || barcodes.length == 0) return barcodes;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : barcodes) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] re = new int[barcodes.length];
        int i = 0;
        while (!pq.isEmpty()) {
            int[] array = pq.poll();
            for (int j = 0; j < array[1]; j++) {
                re[i] = array[0];
                i += 2;
                if (i >= barcodes.length) {
                    i = 1;
                }
            }
        }
        return re;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DistantBarcodes().rearrangeBarcodes(new int[]{1, 2, 1, 1, 1, 1, 1, 3, 3, 3, 3})));
    }
}
