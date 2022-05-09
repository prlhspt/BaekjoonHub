import java.util.*;
import java.io.*;


// cctv는 4 방향이 가능
// cctv별로 각각 0, 1, 2, 3 일 때를 선택

public class Main {

    static int n;
    static int m;
    static int ans = 987654321;

    static int cctvCnt = 0;

    static int[][] map;
    static int[][] rotateArr;
    
    static int[][] copy;

    static List<int[]> camList = new ArrayList<>();

    static boolean[][] vis;

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static int[][] deepCopy(int[][] arr) {
        int[][] result = new int[arr.length][];
        
        for (int i = 0; i < arr.length; i++) {
            result[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return result;
    }

    public static void checkVision(int r, int c, int camNum, int rotate) {

        if(camNum == 5) {

            for (int i = 0; i < 4; i++) {
                int nx = r;
                int ny = c;
                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (copy[nx][ny] == 6) break;

                    copy[nx][ny] = 5;
                }
            }
        } else if (camNum == 4) {
            
            // 2, 3, 4
            for (int i = rotate; i < rotate + 3; i++) {
                int remain = i % 4;
                int nx = r;
                int ny = c;
                while (true) {
                    nx += dx[remain];
                    ny += dy[remain];
                    
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (copy[nx][ny] == 6) break;
                    
                    copy[nx][ny] = 4;
                }    
                
            }
        } else if (camNum == 3) {
            for (int i = rotate; i < rotate + 2; i++) {
                int remain = i % 4;
                int nx = r;
                int ny = c;
                while (true) {
                    nx += dx[remain];
                    ny += dy[remain];
                    
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (copy[nx][ny] == 6) break;
                    
                    copy[nx][ny] = 3;
                }    
            }    
        } else if (camNum == 2) {
            for (int i = rotate; i < 4; i+=2) {
                int nx = r;
                int ny = c;
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                    
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (copy[nx][ny] == 6) break;
                    
                    copy[nx][ny] = 2;
                }
            }
        } else if (camNum == 1) {
            int nx = r;
            int ny = c;
            while (true) {
                nx += dx[rotate];
                ny += dy[rotate];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                if (copy[nx][ny] == 6) break;
                
                copy[nx][ny] = 1;
            }    
        }

    }

    public static void solve() {
        // rotate로 어느 방향 밝힐지 판단, 조합으로 0,1,2,3 다 돔
        // 카메라 번호로 어느 방향만큼 밝힐지 판단,
        // rotate 방향 + 카메라 번호로 어떻게 밝힐지
        copy = deepCopy(map);
        
        for (int[] cam : camList) {
            int r = cam[0];
            int c = cam[1];

            int camNum = map[r][c];
            int rotate = rotateArr[r][c];

            checkVision(r, c, camNum, rotate);
        }
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) cnt++;
            }
        }
        
        ans = Math.min(ans, cnt);
        
    }

    public static void combination(int k, int st) {

        if (k == camList.size()) {
            solve();

            return;
        }

        // 1,3,4는 4번, 2는 2번, 5는 1번
        for (int i = st; i < n*m; i++) {
            int r = i / m;
            int c = i % m;

            if (rotateArr[r][c] == -1 || vis[r][c]) continue;

            int ori = rotateArr[r][c];

            for (int j = rotateArr[r][c]; j >= 0; j--) {
                if (j <= 0) break;
                vis[r][c] = true;
                rotateArr[r][c]--;
                combination(k+1, i+1);

            }
            vis[r][c] = false;
            rotateArr[r][c] = ori;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        rotateArr = new int[n][m];

        vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0 || map[i][j] == 6)
                    rotateArr[i][j] = -1;
                else {
                    camList.add(new int[]{i, j});

                    if (map[i][j] == 5) rotateArr[i][j] = 1;
                    else if (map[i][j] == 2) rotateArr[i][j] = 2;
                    else rotateArr[i][j] = 4;
                }

            }
        }

        combination(0, 0);
        
        System.out.print(ans);

    }
}