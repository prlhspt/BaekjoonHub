import java.util.*;
import java.io.*;

public class Main {

    static int r;
    static int c;
    
    static int pr;
    static int pc;
    
    static int cr;
    static int cc;
    
    static int n;
    
    static int[][] board;
    static int[][] puz;
    
    
    public static boolean inputCheck() {
        for (int i = 0; i < 4; i++) {
            if (i != 0) rotate();
            
            for (int a = 0; a <= r-pr; a++) {
                for (int b = 0; b <= c-pc; b++) {
                    if (compare(a, b)) {
                        cr = a;
                        cc = b;
                        return true;   
                    }
                }
            }
        }
        return false;
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

    public static int[][] deepCopy(int[][] arr) {
        int[][] res = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return res;
    }
    
    public static void rotate() {
        int rr = pc;
        int rc = pr;
        
        int[][] copyPuz = new int[rr][rc];
        
        for (int i = 0; i < pr; i++) {
            for (int j = 0, k = 0; j < pc; j++, k++) {
                copyPuz[k][pr-1-i] = puz[i][j];
            }
        }
       
        puz = new int[rr][rc];
        puz = deepCopy(copyPuz);
        pr = rr;
        pc = rc;
        
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
            
            if (inputCheck()) {
                for (int a = cr; a < cr+pr; a++) {
                    for (int b = cc; b < cc+pc; b++) {
                        if (puz[a-cr][b-cc] == 1) {
                            board[a][b] = 1;
                        }
                    }
                }
            }
            
        }
        
        int ans = 0;
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 1) ans++;
            }
        }
        
        System.out.print(ans);

    }
}