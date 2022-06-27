import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class Solution {

    class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public boolean solve(String[] place) {

        char[][] graph = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                graph[i][j] = place[i].charAt(j);
            }
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (graph[i][j] == 'P') {
                    boolean[][] visited = new boolean[5][5];
                    Queue<Node> queue = new ArrayDeque<>();
                    queue.offer(new Node(i, j, 0));
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        Node now = queue.poll();

                        if (now.count == 2) {
                            continue;
                        }

                        for (int a = 0; a < 4; a++) {
                            int nx = now.x + dx[a];
                            int ny = now.y + dy[a];

                            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                            if (visited[nx][ny]) continue;
                            if (graph[nx][ny] == 'X') continue;

                            visited[nx][ny] = true;

                            if (now.count <= 1 && graph[nx][ny] == 'P') {
                                return false;
                            }

                            queue.offer(new Node(nx, ny, now.count + 1));

                        }

                    }
                }
            }
        }
        return true;
    }

    public int[] solution(String[][] places) {

        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            if (solve(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
}