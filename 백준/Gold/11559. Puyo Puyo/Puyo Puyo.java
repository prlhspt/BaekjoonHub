import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] arr;

    static int answer = 0;

    static boolean boomFlag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[12][6];

        for (int i = 0; i < 12; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < 6; j++) {
                if (s.charAt(j) == '.') {
                    arr[11 - i][j] = -1;
                } else if (s.charAt(j) == 'R') {
                    arr[11 - i][j] = 0;
                } else if (s.charAt(j) == 'G') {
                    arr[11 - i][j] = 1;
                } else if (s.charAt(j) == 'B') {
                    arr[11 - i][j] = 2;
                } else if (s.charAt(j) == 'P') {
                    arr[11 - i][j] = 3;
                } else if (s.charAt(j) == 'Y') {
                    arr[11 - i][j] = 4;
                }
            }
        }

        checkBlock();

        System.out.print(answer);

    }

    private static void checkBlock() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != -1) {
                     bfs(i, j);
                }
            }
        }

        if (boomFlag) {
            boomFlag = false;
            answer++;
            upBlock();
            checkBlock();
            return;
        }

    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> temp = new ArrayList<>();

        boolean[][] visited = new boolean[12][6];

        int curColor = arr[x][y];

        int count = 0;

        queue.offer(new Node(x, y));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (OOB(nx, ny) || visited[nx][ny] || arr[nx][ny] == -1 || arr[nx][ny] != curColor) continue;

                visited[nx][ny] = true;
                count++;
                queue.offer(new Node(nx, ny));
                temp.add(new Node(nx, ny));

            }
        }

        if (count >= 4) {
            boomFlag = true;
            for (Node t : temp) {
                arr[t.x][t.y] = -1;
            }
        }

    }

    private static void upBlock() {
        for (int i = 0; i < 6; i++) {

            int fix = 0;
            int move = 0;

            for (int j = 0; j < 12; j++) {
                if (arr[j][i] == -1) {
                    fix = j;
                    move = j;
                    break;
                }
            }

            while (++move < 12) {
                if (arr[fix][i] == -1 && arr[move][i] != -1) {
                    arr[fix][i] = arr[move][i];
                    arr[move][i] = -1;
                    fix++;
                }
            }

        }
    }

    private static boolean OOB(int x, int y) {

        if (x < 0 || x >= 12 || y < 0 || y >= 6) {
            return true;
        }
        return false;
    }
}
