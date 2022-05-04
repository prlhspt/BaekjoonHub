import java.io.*;

class Main {

    static int N;
    static int[][] star;

    public static void solve(int r, int c, int n) {

        if (n == 3) {
            for (int i = r, j = 0; i < r + n; i++, j++) {
                star[i][c+j] = 1;
                star[i][c-j] = 1;
            }

            for (int i = 0; i < 2; i++) {
                star[r+n-1][c+i] = 1;
                star[r+n-1][c-i] = 1;
            }

            return;
        }

        int d = n / 2;

        solve(r, c, n/2);
        solve(r + d, c - d, n/2);
        solve(r + d, c + d, n/2);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // r : n
        // c/2 : n

        star = new int[N][N*2-1];

        solve(0, (N*2-1)/2, N);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N*2-1; j++) {
                if (star[i][j] == 1) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        
        System.out.println(sb);

    }
}