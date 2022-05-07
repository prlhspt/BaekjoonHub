import java.util.*;
import java.io.*;

public class Main {

    static int[][] arr = new int[5][5];
    static int ans = 0;

    static boolean[][] pick = new boolean[5][5];

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    static void combination(int k, int st) {
        if (k == 7) {
            bfs();
            return;
        }

        for (int i = st; i < 25; i++) {
            int r = i / 5;
            int c = i % 5;
            pick[r][c] = true;
            combination(k+1, i+1);
            pick[r][c] = false;
        }
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque();
        boolean[][] vis = new boolean[5][5];
        int cnt = 0, checkS = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                 if (q.isEmpty() && pick[i][j]) {
                     q.offer(new int[]{i , j});
                     vis[i][j] = true;
                 }
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            cnt++;
            if (arr[x][y] == 1) checkS++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx > 4 || ny < 0 || ny > 4 || !pick[nx][ny] || vis[nx][ny]) continue;
                q.offer(new int[]{nx, ny});
                vis[nx][ny] = true;
            }
        }

        if (cnt >= 7 && checkS >= 4) {
            ans++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (s.charAt(j) == 'S') arr[i][j] = 1;
                else arr[i][j] = -1;
            }
        }

        combination(0, 0);
        System.out.print(ans);

    }
}