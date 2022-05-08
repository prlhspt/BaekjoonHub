import java.util.*;
import java.io.*;

public class Main {

    static int[][] arr;
    static boolean[][] colorArr;

    static boolean[] vis1;
    static boolean[] vis2;

    static int[] res = new int[2];
    static int n;

    public static void solve(int k, int st, boolean color) {

        for (int i = st; i < n*n; i++) {

            int r = i / n;
            int c = i % n;

            if (arr[r][c] == 0 || colorArr[r][c] != color) continue;
            if (vis1[r+c] || vis2[r-c+n-1]) continue;

            vis1[r+c] = true;
            vis2[r-c+n-1] = true;
            solve(k+1, i+1, color);
            vis1[r+c] = false;
            vis2[r-c+n-1] = false;
        }

        res[color ? 1 : 0] = Math.max(res[color ? 1 : 0], k);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        colorArr = new boolean[n][n];

        vis1 = new boolean[n*2-1];
        vis2 = new boolean[n*2-1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                colorArr[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
            }
        }

        solve(0, 0, true);
        solve(0, 0, false);

        System.out.println(res[0] +  res[1]);

    }
}