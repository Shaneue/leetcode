package medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 *
 * 拓扑排序，主要有bfs跟dfs方法。
 * 遍历依赖之前，先设置visited为-1
 * bfs时，取入度为0的节点进行遍历。
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Queue<Integer>> graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            Queue<Integer> q = graph.getOrDefault(edge[0], new LinkedList<>());
            q.add(edge[1]);
            graph.put(edge[0], q);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visit(graph, visited, i)) return false;
        }
        return true;
    }

    boolean visit(Map<Integer, Queue<Integer>> graph, int[] visited, int i) {
        if (visited[i] == -1) return false;
        if (visited[i] == 1) return true;
        Queue<Integer> q = graph.get(i);
        visited[i] = -1;
        if (q != null) {
            while (!q.isEmpty()) {
                if (!visit(graph, visited, q.poll())) return false;
            }
        }
        visited[i] = 1;
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule c = new CourseSchedule();
        System.out.println(c.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(c.canFinish(2, new int[][]{{1, 0}}));
    }
}
