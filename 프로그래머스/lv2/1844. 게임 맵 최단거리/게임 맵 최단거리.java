import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    class Node {
        private int x;
        private int y;
        private int count;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCount() {
            return count;
        }

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    int[] DX = {-1, 0, 1, 0};
    int[] DY = {0, -1, 0, 1};
    int N;
    int M;

    public int findRoad(int[][] maps) {

        boolean[][] visited = new boolean[N][M];

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.getX() + DX[i];
                int ny = now.getY() + DY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny] == 0) continue;

                if (nx == N - 1 && ny == M - 1) {
                    return now.getCount() + 1;
                }
                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, now.getCount() + 1));
            }
        }
        return -1;

    }

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        int answer = findRoad(maps);

        return answer;
    }
}