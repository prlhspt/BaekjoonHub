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

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int sum = arr[0];
        int en = 0;
        int min = 2000000001;

        for (int st = 0; st < n; st++) {
            while (en < n && sum < m) {
                en++;
                if (en != n) {
                    sum += arr[en];
                }
            }
            if (en == n) {
                break;
            }
            min = Math.min(min, en - st + 1);
            sum -= arr[st];
        }

        if (min == 2000000001) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}