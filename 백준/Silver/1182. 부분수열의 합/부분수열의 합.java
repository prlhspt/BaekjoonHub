import java.util.*;
import java.io.*;;

class Main {

    static int N, S;
    static int[] arr;
    static int answer = 0;

    static boolean[] visited;
    public static void solve(int cur, int tot) {

        if (cur == N) {
            if (tot == S) answer++;
            return;
        }

        solve(cur+1, tot);
        solve(cur+1, tot+arr[cur]);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);
        if (S == 0) answer--;

        System.out.println(answer);

    }
}