import java.io.*;

class Main {

    static int N;
    static int[][] video;
    static StringBuilder sb = new StringBuilder();

    public static boolean check(int r, int c, int n) {
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (video[r][c] != video[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void solve(int r, int c, int n) {

        if (check(r, c, n)) {
            sb.append(video[r][c]);
            return;
        }

        int d = n / 2;

        sb.append("(");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                solve(r + i * d, c + j * d, d);
            }
        }
        sb.append(")");

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        video = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                video[i][j] = s.charAt(j) - '0';
            }
        }

        solve(0, 0, N);
        System.out.println(sb.toString());

    }

}