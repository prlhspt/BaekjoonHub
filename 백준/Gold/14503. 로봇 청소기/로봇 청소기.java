import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int ans = 1;

    public static int n = 0;
    public static int m = 0;
    public static int[][] map;

    public static int rotate(int d) {
        if (d == 0) {
            d = 3;
        } else {
            d--;
        }
        return d;
    }

    public static int cnt = 1;

    public static boolean exitFlag = false;

    public static void dfs(int r, int c, int d) {
        if (exitFlag) {
            return;
        }

        boolean rotateFlag = false;

         for (int i = 0; i < 4; i++) {
             if (exitFlag) {
                 return;
             }

            d = rotate(d);

            int nr = r + dx[d];
            int nc = c + dy[d];

             if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
             if (map[nr][nc] == 1 || map[nr][nc] == 2) continue;

             map[nr][nc] = 2;
             cnt++;
             ans = Math.max(cnt, ans);

            dfs(nr, nc, d);
            rotateFlag = true;
         }

         if (!rotateFlag) {
             int nr = r - dx[d];
             int nc = c - dy[d];

             if (map[nr][nc] != 1) {
                 dfs(nr, nc, d);
             }
             exitFlag = true;
             return;
         }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map[r][c] = 2;
        dfs(r, c, d);
        System.out.println(ans);
    }
}