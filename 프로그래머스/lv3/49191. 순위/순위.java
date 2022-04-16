import java.util.*;

class Solution {

    int count;

    void dfs(List<List<Integer>> graph, boolean[] visited, int start) {

        count++;
        visited[start] = true;
        for (int i = 0; i < graph.get(start).size(); i++) {
            int x = graph.get(start).get(i);
            if (!visited[x]) {
                dfs(graph, visited, x);
            }
        }
    }

    public int solution(int n, int[][] results) {

        int v = n;
        int e = results.length;
        int answer = 0;

        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> revGraph = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            graph.get(results[i][1]).add(results[i][0]);
            revGraph.get(results[i][0]).add(results[i][1]);
        }

        for (int i = 1; i <= n; i++) {

            count = -1;
            boolean[] visited = new boolean[n + 1];
            dfs(graph, visited, i);
            int parent = count;

            count = -1;
            visited = new boolean[n + 1];
            dfs(revGraph, visited, i);
            int child = count;

            if ((parent + child) == (n - 1)) {
                answer++;
            }
        }

        return answer;
    }
}