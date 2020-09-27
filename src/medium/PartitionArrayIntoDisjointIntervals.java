package medium;

/**
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 *
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 *
 * Note:
 *
 * 2 <= A.length <= 30000
 * 0 <= A[i] <= 10^6
 * It is guaranteed there is at least one way to partition A as described.
 */
public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] A) {
        int[] min = new int[A.length];
        min[A.length - 1] = A[A.length - 1];
        int[] max = new int[A.length];
        max[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            max[i] = Math.max(A[i], max[i - 1]);
            min[A.length - 1 - i] = Math.min(A[A.length - 1 - i], min[A.length - i]);
        }
        for (int i = 0; i < A.length - 1; i++) {
            if (min[i + 1] >= max[i]) return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        PartitionArrayIntoDisjointIntervals p = new PartitionArrayIntoDisjointIntervals();
        System.out.println(p.partitionDisjoint(new int[]{1, 2}));
        System.out.println(p.partitionDisjoint(new int[]{1, 1, 1, 0, 6, 12}));
    }
}
