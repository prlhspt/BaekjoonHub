import java.util.*;
import java.io.*;

public class Main {

    static int r;
    static int c;

    static int pr;
    static int pc;

    static int n;

    static int[][] board;
    static int[][] puz;

    static int ans = 0;


    public static boolean inputCheck() {
        for (int i = 0; i <= r - pr; i++) {
            for (int j = 0; j <= c - pc; j++) {
                if (compare(i, j)) {
                    attach(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    public static void attach(int r, int c) {
        for(int i = 0; i < pr; i++) {
            for ( int j = 0; j < pc; j++) {
                if (puz[i][j] == 1) {
                    board[r + i][c + j] = puz[i][j];
                    ans++;
                }
            }
        }
    }

    public static boolean compare(int r, int c) {
        for (int i = 0; i < puz.length; i++) {
            for (int j = 0; j < puz[i].length; j++) {
                if (board[i+r][j+c] == 1 && puz[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] rotate() {
        int[][] copyPuz = new int[pc][pr];

        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                copyPuz[j][pr-1-i] = puz[i][j];
            }
        }

        int tmp = pr;
        pr = pc;
        pc = tmp;

        return copyPuz;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[r][c];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            pr = Integer.parseInt(st.nextToken());
            pc = Integer.parseInt(st.nextToken());

            puz = new int[pr][pc];

            for (int j = 0; j < pr; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < pc; k++) {
                    puz[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for (int tu = 0; tu < 4; tu++) {
                if (inputCheck()) {
                    break;
                }
                puz = rotate();
            }
        }
        System.out.print(ans);
    }
}