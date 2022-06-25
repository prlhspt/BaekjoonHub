import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[][] PICTURE;
    boolean[][] VISITED;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int pictureSizeCheck(int x, int y) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(x, y));
        VISITED[x][y] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx < 0 || nx >= PICTURE.length || ny < 0 || ny >= PICTURE[0].length) continue;
                if (VISITED[nx][ny] || PICTURE[nx][ny] == 0 || PICTURE[nx][ny] != PICTURE[x][y]) continue;

                VISITED[nx][ny] = true;
                count++;
                queue.offer(new Pos(nx, ny));
            }
        }

        return count;
    }

    public int[] solution(int m, int n, int[][] picture) {

        PICTURE = picture;
        VISITED = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !VISITED[i][j]) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, pictureSizeCheck(i, j));
                }
            }
        }


        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}