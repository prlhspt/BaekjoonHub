import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int min = 2000000001;
        int en = 0;

        for (int st = 0; st < n; st++) {
            while (en < n && arr[en] - arr[st] < m) {
                en++;
            }
            if (en == n) {
                break;
            }
            min = Math.min(min, arr[en] - arr[st]);
        }

        System.out.println(min);
    }
}