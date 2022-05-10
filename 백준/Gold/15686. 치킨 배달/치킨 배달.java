import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int x;
        int y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int m;
    
    static int[][] street;
    
    static int ans = 987654321;
    
    static List<Node> chicken = new ArrayList<>();
    static List<Node> home = new ArrayList<>();
    static List<Node> pick = new ArrayList<>();
    
    public static void checkDistance() {
        int sum = 0;
        
        for (Node h : home) {
            int hCnt = 987654321;
            for (Node p : pick) {
                hCnt = Math.min(hCnt, Math.abs(h.x - p.x) + Math.abs(h.y - p.y));;
            }

            sum += hCnt;
            
        }
        
        ans = Math.min(ans, sum);
    }
    
    public static void solve(int k, int st) {
        if (k == m) {
            checkDistance();
            return;
        }
        
        for (int i = st; i < chicken.size(); i++) {
            
            Node now = chicken.get(i);
            
            street[now.x][now.y] = 2;
            pick.add(now);
            
            solve(k+1, i+1);
            
            street[now.x][now.y] = 0;
            pick.remove(now);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        

        street = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                street[i][j] = Integer.parseInt(st.nextToken());
                if (street[i][j] == 2) {
                    street[i][j] = 0;
                    chicken.add(new Node(i, j));
                } else if (street[i][j] == 1) {
                    home.add(new Node(i, j));
                }
            }
        }
        
        solve(0, 0);
        
        System.out.print(ans);
        
    }
}