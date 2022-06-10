import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static int curTime = 0;
    public static boolean exitFlag = false;

    public static class Snake {
        int x;
        int y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Command {
        int time;
        int dir;

        public Command(int time, int dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int left(int d) {
        if (d == 0) {
            d = 3;
        } else {
            d--;
        }
        return d;
    }

    public static int right(int d) {
        if (d == 3) {
            d = 0;
        } else {
            d++;
        }
        return d;
    }

    private static void func(int[][] map, Deque<Snake> snakes, Queue<Command> command, int x, int y, int d) {
        if (exitFlag) {
            return;
        }

        curTime++;

        map[x][y] = 1;

        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= map.length || ny < 0 || ny >= map.length
        || map[nx][ny] == 1) {
            exitFlag = true;
            return;
        }

        if (map[nx][ny] == 2) { // 사과가 있으니까 꼬리 변화 없음
            snakes.offerFirst(new Snake(nx, ny));
            map[x][y] = 1;
        } else if (map[nx][ny] != 2) { // 꼬리 한칸 땡겨야 함
            snakes.offerFirst(new Snake(nx, ny));
            Snake snake = snakes.pollLast();

            map[snake.x][snake.y] = 0;
        }

        if (command.isEmpty()) {
            func(map, snakes, command, nx, ny, d);
            exitFlag = true;
            return;
        }

        Command now = command.peek();

        if (curTime < now.time) {
            func(map, snakes, command, nx, ny, d);
        } else {
            now = command.poll();

            if (now.dir == -1) {
                d = left(d);
            } else {
                d = right(d);
            }

            func(map, snakes, command, nx, ny, d);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a-1][b-1] = 2;
        }

        int l = Integer.parseInt(br.readLine());

        Queue<Command> command = new ArrayDeque<>();

        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            String dir = st.nextToken();
            if (dir.equals("L")) {
                command.offer(new Command(a, -1));
            } else {
                command.offer(new Command(a, 1));
            }
        }

        Deque<Snake> snakes = new ArrayDeque<>();
        snakes.add(new Snake(0, 0));

        func(map, snakes, command, 0, 0, 1);

        System.out.print(curTime);
    }
}