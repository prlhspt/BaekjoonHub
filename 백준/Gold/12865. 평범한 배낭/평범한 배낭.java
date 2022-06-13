import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int solve(int[] weighs, int[] values, int N, int W) {

        int[][] dp = new int[N+1][W+1];

        for (int n = 1; n < N + 1; n++) {
            for (int w = 1; w < W + 1; w++) {
                if (weighs[n-1] <= w) {
                    dp[n][w] = Math.max(values[n - 1] + dp[n - 1][w - weighs[n - 1]], dp[n - 1][w]);
                } else {
                    dp[n][w] = dp[n-1][w];
                }
            }

        }

        return dp[N][W];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int answer = solve(weights, values, N, W);
        System.out.println(answer);

    }

}