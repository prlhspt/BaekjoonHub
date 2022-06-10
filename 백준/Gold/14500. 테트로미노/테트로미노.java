import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static int answer;

    public static class Block {
        int r;
        int c;
        int[][] paper;
        int rotateNum;

        public Block(int r, int c, int[][] paper, int rotateNum) {
            this.r = r;
            this.c = c;
            this.paper = paper;
            this.rotateNum = rotateNum;
        }
    }

    public static Block rotate(Block block) {
        int r = block.r;
        int c = block.c;

        int[][] retArr = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                retArr[j][i] = block.paper[r - i - 1][j];
            }
        }

        return new Block(c, r, retArr, block.rotateNum);
    }

    public static int sumMapValue(int[][] map, Block block, int r, int c) {
        int count = 0;
        for (int i = 0; i < block.r; i++) {
            for (int j = 0; j < block.c; j++) {
                if (block.paper[i][j] == 1) {
                    count += map[r+i][c+j];
                }
            }
        }
        return count;

    }

    public static void func(int[][] map, Queue<Block> queue, int n, int m) {
        while (!queue.isEmpty()) {
            Block now = queue.poll();
            for (int i = 0; i < now.rotateNum; i++) {
                now = rotate(now);

                int nr = n - now.r;
                int nc = m - now.c;

                for (int a = 0; a <= nr; a++) {
                    for (int b = 0; b <= nc; b++) {
                        int count = sumMapValue(map, now, a, b);
                        answer = Math.max(count, answer);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Block> queue = new ArrayDeque<>();

        int[][] paper = new int[1][4];
        paper[0][0] = 1;
        paper[0][1] = 1;
        paper[0][2] = 1;
        paper[0][3] = 1;
        queue.offer(new Block(1, 4, paper, 2));

        paper = new int[2][2];
        paper[0][0] = 1;
        paper[0][1] = 1;
        paper[1][0] = 1;
        paper[1][1] = 1;

        queue.offer(new Block(2, 2, paper, 1));

        paper = new int[3][2];
        paper[0][0] = 1;
        paper[1][0] = 1;
        paper[2][0] = 1;
        paper[2][1] = 1;
        queue.offer(new Block(3, 2, paper, 4));

        paper = new int[3][2];
        paper[0][1] = 1;
        paper[1][1] = 1;
        paper[2][0] = 1;
        paper[2][1] = 1;
        queue.offer(new Block(3, 2, paper, 4));

        paper = new int[3][2];
        paper[0][0] = 1;
        paper[1][0] = 1;
        paper[1][1] = 1;
        paper[2][1] = 1;
        queue.offer(new Block(3, 2, paper, 2));

        paper = new int[3][2];
        paper[0][1] = 1;
        paper[1][0] = 1;
        paper[1][1] = 1;
        paper[2][0] = 1;
        queue.offer(new Block(3, 2, paper, 2));

        paper = new int[2][3];
        paper[0][0] = 1;
        paper[0][1] = 1;
        paper[0][2] = 1;
        paper[1][1] = 1;
        queue.offer(new Block(2, 3, paper, 4));

        func(map, queue, n, m);

        System.out.println(answer);
    }
}