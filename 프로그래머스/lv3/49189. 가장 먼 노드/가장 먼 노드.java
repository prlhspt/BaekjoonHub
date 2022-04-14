import java.util.*;

class Solution {

    public int solution(int n, int[][] edge) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        int[] level = new int[n+1];
        Arrays.fill(level, n + 1);

        boolean[] visited = new boolean[n+1];

        for (int i = 0; i < edge.length; i++) {
            map.computeIfAbsent(edge[i][0], k -> new ArrayList<>()).add(edge[i][1]);
            map.computeIfAbsent(edge[i][1], k -> new ArrayList<>()).add(edge[i][0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();

        int[] ints = Arrays.stream(edge).min(Comparator.comparingInt(o -> o[0])).get();

        queue.offer(ints[0]);
        level[ints[0]] = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (visited[poll]) {
                continue;
            }

            visited[poll] = true;
            if (map.get(poll) != null) {
                for (int i = 0; i < map.get(poll).size(); i++) {
                    int curVal = map.get(poll).get(i);
                    if (!visited[curVal]) {
                        level[curVal] = Math.min(level[curVal], level[poll] + 1);
                        queue.offer(curVal);
                    }
                }
            }
        }

        int maxVal = 0;
        int result = 0;

        for (int i = 1; i < level.length; i++) {
            if (level[i] != n + 1 && maxVal < level[i]) {
                maxVal = level[i];
                result = 1;

            } else if (level[i] != n + 1 && maxVal == level[i]) {
                result++;
            }
        }

        return result;
    }
}