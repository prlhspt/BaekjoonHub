import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int ans = 0;

    static int[][] board;

    static int[][] deepCopy(int[][] arr) {
        int[][] res = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return res;
    }

    static boolean OOB(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    static int[][] rotate() {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = board[n-1-j][i];
            }
        }
        return res;
    }

    static void tilt(int dir) {
        while(dir-- > 0) board = rotate();
        for (int i = 0; i < n; i++) {
            int[] tilted = new int[n];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                if (tilted[idx] == 0) {
                    tilted[idx] = board[i][j];
                } else if (tilted[idx] == board[i][j]) {
                    tilted[idx++] *= 2;
                } else {
                    tilted[++idx] = board[i][j];
                }
            }
            board[i] = Arrays.copyOf(tilted, tilted.length);
        }
    }

    public static void solve(int k) {
        if (k == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(board[i][j], ans);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] copy = deepCopy(board);
            for (int j = 0; j < i; j++) {
                rotate();
            }
            tilt(i);
            solve(k + 1);
            board = copy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0);

        System.out.print(ans);
    }
}