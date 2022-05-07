import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static int[] s;
    static int[] w;
    
    static int ans = 0;
    static int cnt = 0;
    
    public static void func(int k) {
        if (k == N) {
            ans = Math.max(ans, cnt);
            return;
        }
        // 이미 깨져있거나 하나빼고 다 깼을 때 
        // cnt가 N-1 이라는건 하나빼고 다 깨져서 더 깰게 없다는 것
        if (s[k] <= 0 || cnt == N-1) {
            func(k+1);
            return;
        };
        
        for (int i = 0; i < N; i++) {
            if (s[i] <= 0 || i == k) continue;
            s[k] -= w[i];
            s[i] -= w[k];
            if (s[k] <= 0) cnt++;
            if (s[i] <= 0) cnt++;
            func(k+1);
            if (s[k] <= 0) cnt--;
            if (s[i] <= 0) cnt--;
            s[k] += w[i];
            s[i] += w[k];
        }
        
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        s = new int[N];
        w = new int[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
        }
        
        func(0);
        
        System.out.println(ans);

    }
}