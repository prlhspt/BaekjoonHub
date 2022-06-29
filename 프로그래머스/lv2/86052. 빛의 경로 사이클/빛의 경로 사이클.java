import java.util.*;

class Solution {

    int[] DX = {-1, 0, 1, 0};
    int[] DY = {0, -1, 0, 1};

    int N;
    int M;

    Set<List<Integer>> SET = new LinkedHashSet<>();

    class Node {
        int x;
        int y;
        int dir;

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public Node go(Node node, String[][] graph) {
        int nx = node.x + DX[node.dir];
        int ny = node.y + DY[node.dir];
        int dir = node.dir;

        if (nx == -1) nx = N - 1;
        if (nx == N) nx = 0;
        if (ny == -1) ny = M - 1;
        if (ny == M) ny = 0;

        if (graph[nx][ny].equals("L")) {
            dir++;
            if (dir == 4) {
                dir = 0;
            }
        }

        if (graph[nx][ny].equals("R")) {
            dir--;
            if (dir == -1) {
                dir = 3;
            }
        }
        return new Node(nx, ny, dir);
    }

    public int solve(Node node, String[][] graph) {

        int count = 0;

        Node copy = new Node(node.x, node.y, node.dir);

        while (true) {
            count++;

            if (!SET.add(List.of(copy.x, copy.y, copy.dir))) {
                return count;
            }

            Node goNode = go(copy, graph);
            copy = goNode;

        }
    }

    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();

        String[][] graph = new String[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                graph[i][j] = String.valueOf(grid[i].charAt(j));
            }
        }

        List<Integer> answer = new ArrayList<>();

        // grid 하나하나마다 전부 방향 돌려
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (SET.add(List.of(i, j, dir))) {

                        Node goNode = go(new Node(i, j, dir), graph);
                        int count = solve(goNode, graph);
                        if (count != 0) {
                            answer.add(count);
                        }
                    }
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

}