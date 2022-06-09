import java.util.*;

class Main {
    static class Node {
        int z;
        int x;
        int y;
        int count;

        public Node(int z, int x, int y, int count) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int dx[] = {0, 0, 0, 0, -1, 1};
    static int dy[] = {0, 0, -1, 1, 0, 0};
    static int dz[] = {-1, 1, 0, 0, 0, 0};

    static boolean[] rotateVis = new boolean[5];
    static boolean[] stackVis = new boolean[5];
    static int[] stackRes = new int[5];


    static int answer = 987654321;

    static int[][][] arr;

    static void rotate(int z) {
        int[][] tmp = arr[z];
        int[][] retArr = new int[5][5];

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                retArr[i][j] = tmp[4 - j][i];
            }
        }

        arr[z] = retArr;
    }

    static int[][][] deepCopy() {
        int[][][] retArr = new int[5][5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    retArr[i][j][k] = arr[i][j][k];
                }
            }
        }

        return retArr;
    }

    static void bfs(int[][][] copy) {
        Queue<Node> queue = new ArrayDeque<>();

        boolean[][][] bfsVis = new boolean[5][5][5];

        bfsVis[0][0][0] = true;
        queue.offer(new Node(0, 0, 0, 0));


        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nz = now.z + dz[i];
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nz == 4 && nx == 4 && ny == 4) {
                    answer = Math.min(answer, now.count + 1);
                    continue;
                }

                if (nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (bfsVis[nz][nx][ny] || copy[nz][nx][ny] == 0) continue;

                bfsVis[nz][nx][ny] = true;
                queue.offer(new Node(nz, nx, ny, now.count + 1));
            }
        }
    }

    static int[][][] makeArr() {
        int[][][] copy = deepCopy();
        int[][][] retArr = new int[5][5][5];

        for (int i = 0; i < 5; i++) {
            retArr[i] = copy[stackRes[i]];
        }

        return retArr;
    }

    public static void stackPerm(int k) {

        if (k == 5) {
            int[][][] bfsArr = makeArr();

            if (bfsArr[0][0][0] == 1 && bfsArr[4][4][4] == 1) {
                bfs(bfsArr);
            }
        }

        for (int i = 0; i < 5; i++) {
            if (stackVis[i]) continue;

            stackVis[i] = true;
            stackRes[k] = i;
            stackPerm(k + 1);
            stackVis[i] =false;
            stackRes[k] = 0;
        }

    }

    public static void rotatePerm(int k) {
        if (k == 5) {
            stackPerm(0);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (rotateVis[k]) continue;

            int[][][] copy = deepCopy();
            for (int j = 0; j < i; j++) {
                rotate(k);
            }

            rotateVis[k] = true;
            rotatePerm(k+1);
            rotateVis[k] = false;

            arr = copy;

        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        arr = new int[5][5][5];

        for (int z = 0; z < 5; z++) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    arr[z][x][y] = sc.nextInt();
                }
            }
        }

        rotatePerm(0);

        if (answer == 987654321) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}