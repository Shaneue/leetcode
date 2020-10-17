package medium;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * <p>
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> g = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String s = equations.get(i).get(0);
            String e = equations.get(i).get(1);
            double val = values[i];
            dfs(g, s, e, val);
        }
        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s = queries.get(i).get(0);
            String e = queries.get(i).get(1);
            ret[i] = g.get(s) == null ? -1.0 : (g.get(s).get(e) == null ? -1.0 : g.get(s).get(e));
        }
        return ret;
    }

    void dfs(Map<String, Map<String, Double>> g, String s, String e, double value) {
        g.computeIfAbsent(s, k -> new HashMap<>()).put(e, value);
        if (g.containsKey(e)) {
            for (String next : g.get(e).keySet()) {
                if (!g.get(s).containsKey(next)) {
                    double newValue = value * g.get(e).get(next);
                    g.get(s).put(next, newValue);
                    dfs(g, s, next, newValue);
                }
            }
        }
        if (!g.containsKey(e) || !g.get(e).containsKey(s))
            dfs(g, e, s, 1 / value);
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        equations.add(Arrays.asList("c", "d"));
        double[] values = new double[]{1, 2, 3};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "d"));
        double[] ans = new EvaluateDivision().calcEquation(equations, values, queries);
        for (double an : ans) {
            System.out.println(an);
        }
    }
}
