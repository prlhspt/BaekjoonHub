import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static Queue<Node> QUEUE = new ArrayDeque();
    public static int[][] MAP;
    public static int N;
    public static int M;

    public static int[] DX = {-1, 0, 1, 0};
    public static int[] DY = {0, 1, 0, -1};

    public static int ANSWER = 0;

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void virusCheck(Queue<Node> queue) {

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + DX[i];
                int ny = now.y + DY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (MAP[nx][ny] != 0) continue;

                MAP[nx][ny] = 2;
                queue.offer(new Node(nx, ny));
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0) {
                    count++;
                }
            }
        }
        ANSWER = Math.max(ANSWER, count);

    }

    public static Queue<Node> copyQueue(Queue<Node> queue) {
        Queue<Node> copyQueue = new ArrayDeque<>();
        Iterator<Node> it = queue.iterator();
        while (it.hasNext()) {
            copyQueue.offer(it.next());
        }
        return copyQueue;
    }

    public static int[][] copyArray() {
        int[][] copyArray = new int[N][];
        for (int i = 0; i < N; i++) {
            copyArray[i] = Arrays.copyOf(MAP[i], MAP[i].length);
        }
        return copyArray;
    }

    public static void func(int k, int cur) {
        if (k == 3) {
            Queue<Node> copyQueue = copyQueue(QUEUE);
            virusCheck(copyQueue);
        }

        for (int i = cur; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if (MAP[r][c] != 0) continue;

            int[][] copyArray = copyArray();
            MAP[r][c] = 1;
            func(k + 1, i + 1);
            MAP = copyArray;
            MAP[r][c] = 0;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                if (MAP[i][j] == 2) {
                    QUEUE.offer(new Node(i, j));
                }
            }
        }

        func(0, 0);

        System.out.println(ANSWER);

    }
}