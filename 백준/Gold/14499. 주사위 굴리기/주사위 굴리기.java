import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static class Dice {
        int a, b, c, d, e, f;

        public Dice() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
        }
        
        public void up() {
            int temp = a;
            a = f;
            f = e;
            e = c;
            c = temp;
        }

        public void down() {
            int temp = a;
            a = c;
            c = e;
            e = f;
            f = temp;
        }

        public void left() {
            int temp = b;
            b = f;
            f = d;
            d = c;
            c = temp;
        }

        public void right() {
            int temp = b;
            b = c;
            c = d;
            d = f;
            f = temp;
        }

        public void move(int dir) {
            if (dir == 0) {
                right();
            } else if (dir == 1) {
                left();
            } else if (dir == 2) {
                up();
            } else {
                down();
            }
        }

    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        int[] commands = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        Dice dice = new Dice();

        for (int i = 0; i < k; i++) {
            int com = commands[i];
            int nx = x + dx[com];
            int ny = y + dy[com];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            x = nx;
            y = ny;

            dice.move(com);
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice.c;
            } else {
                dice.c = map[nx][ny];
                map[nx][ny] = 0;
            }
            System.out.println(dice.f);
        }
    }
}