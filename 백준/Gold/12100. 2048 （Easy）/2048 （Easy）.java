import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int ans = 0;

    static int[][] board;
    static int[][] copyBoard;

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

    static void tilt1() {
        int[] arr = {0, 2, 0, 2, 8, 8, 0, 16};
        int[] tilted = new int[8];
        int idx = 0;
        for (int i = 0; i < 8; i++) {
            if (arr[i] == 0) continue;
            if (tilted[idx] == 0) {
                tilted[idx] = arr[i];
            } else if (tilted[idx] == arr[i]) {
                tilted[idx] *= 2;
                idx++;
            } else {
                idx++;
                tilted[idx] = arr[i];
            }
        }
        System.out.println(Arrays.toString(tilted));
    }

    static int[][] rotate() {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = copyBoard[n-1-j][i];
            }
        }
        return res;
    }

    static void tilt(int dir) {
        while(dir-- > 0) copyBoard = rotate();
        for (int i = 0; i < n; i++) {
            int[] tilted = new int[n];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (copyBoard[i][j] == 0) continue;
                if (tilted[idx] == 0) {
                    tilted[idx] = copyBoard[i][j];
                } else if (tilted[idx] == copyBoard[i][j]) {
                    tilted[idx++] *= 2;
                } else {
                    tilted[++idx] = copyBoard[i][j];
                }
            }
            copyBoard[i] = Arrays.copyOf(tilted, tilted.length);
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
        for (int i = 0; i < 1 << (2 * 5); i++) {
            copyBoard = deepCopy(board);
            int quot = i;
            for (int j = 0; j < 5; j++) {
                int rem = quot % 4;
                quot /= 4;
                tilt(rem);
            }

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    ans = Math.max(copyBoard[a][b], ans);
                }
            }
        }
        System.out.print(ans);
    }
}