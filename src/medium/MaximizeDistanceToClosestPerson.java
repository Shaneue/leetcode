package medium;

/**
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to the closest person.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: seats = [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Example 3:
 *
 * Input: seats = [0,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= seats.length <= 2 * 10^4
 * seats[i] is 0 or 1.
 * At least one seat is empty.
 * At least one seat is occupied.
 */
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int[] left = new int[seats.length];
        int[] right = new int[seats.length];
        int l = -222222;
        int r = 222222;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) l = i;
            else {
                left[i] = i - l;
            }
            if (seats[seats.length - 1 - i] == 1) r = seats.length - 1 - i;
            else {
                right[seats.length - 1 - i] = r - (seats.length - 1 - i);
            }
        }
        int ret = -222222;
        for (int i = 0; i < seats.length; i++) {
            int d = Math.min(left[i], right[i]);
            ret = Math.max(ret, d);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximizeDistanceToClosestPerson m = new MaximizeDistanceToClosestPerson();
        System.out.println(m.maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
        System.out.println(m.maxDistToClosest(new int[]{1, 0, 0, 0}));
    }
}
