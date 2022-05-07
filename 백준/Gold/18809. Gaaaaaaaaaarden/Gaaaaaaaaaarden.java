import java.util.*;
import java.io.*;

public class Main {
    
    static int[][] arr;
    
    static int n;
    static int m;
    static int g;
    static int r;
    
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    
    static int ans = 0;
    
    static void selectG(int k, int cur) {
        if (k == g) {
            selectR(0, 0);
            return;
        }
        
        for (int i = cur; i < n*m; i++) {
            int r = i / m;
            int c = i % m;
            if (arr[r][c] != 2) continue;
            
            arr[r][c] = 3;
            selectG(k+1, i+1);
            arr[r][c] = 2;
        }
        
    }
    
    static void selectR(int k, int cur) {
        if (k == r) {
            bfs();
            return;
        }
        
        for (int i = cur; i < n*m; i++) {
            int r = i / m;
            int c = i % m;
            if (arr[r][c] != 2) continue;
            
            arr[r][c] = 4;
            selectR(k+1, i+1);
            arr[r][c] = 2;
        }
    }
    
    static void bfs() {

        int[][] copy = deepCopy(arr);
            
        int cnt = 0;
        
        Queue<int[]> qG = new ArrayDeque<>();
        boolean[][] visG = new boolean[n][m];
        int[][] disG = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 3) {
                    qG.offer(new int[]{i, j});
                    visG[i][j] = true;
                }
            }
        }
        
        Queue<int[]> qR = new ArrayDeque<>();
        boolean[][] visR = new boolean[n][m];
        int[][] disR = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 4) {
                    qR.offer(new int[]{i, j});
                    visR[i][j] = true;
                }
            }
        }
        
        while(!qG.isEmpty() || !qR.isEmpty()) {
            
            if (!qG.isEmpty()) {
                int qSize = qG.size();
                
                while (qSize-- != 0) {
                    int[] now = qG.poll();
                    int x = now[0];
                    int y = now[1];
                    
                    visG[x][y] = true;
                    if (copy[x][y] == 5) continue;
                    
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || visG[nx][ny]) continue;
                        if (!(copy[nx][ny] == 1) && !(copy[nx][ny] == 2)) continue;
                        
                        visG[nx][ny] = true;
                        disG[nx][ny] = disG[x][y] + 1;
                        qG.offer(new int[]{nx, ny});
                       
                    }
                
                }
            }
            
            if (!qR.isEmpty()) {
                
                int qSize = qR.size();
                
                while (qSize-- != 0) {
                    int[] now = qR.poll();
                    int x = now[0];
                    int y = now[1];
                    
                    visR[x][y] = true;
                    if (copy[x][y] == 5) continue;
                    
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || visR[nx][ny]) continue;
                        if (!(copy[nx][ny] == 1) && !(copy[nx][ny] == 2)) continue;
                        
                        visR[nx][ny] = true;
                        disR[nx][ny] = disR[x][y] + 1;
                        
                        if (disR[nx][ny] == disG[nx][ny]) {
                            cnt++;
                            copy[nx][ny] = 5;
                        } else {
                            qR.offer(new int[]{nx, ny});
                        }
                    }
                }      
            }
        }
        
        ans = Math.max(ans, cnt);
        
    }
    
    static int[][] deepCopy(int[][] arr) {
        int[][] tmp = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            tmp[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return tmp;
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        arr = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            
        }
        
        selectG(0, 0);
        
        System.out.println(ans);

    }
}