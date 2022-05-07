import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static int[][] arr;
    
    static int[] rest;
    
    static int ans = 0;
    
    public static void func(int k) {
        
        if (k == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (rest[i] <= 0) {
                    cnt++;
                }
            }
            ans = Math.max(cnt, ans);
            return;
        }
        // 이미 깨져있음
        if (rest[k] <= 0) {
            func(k+1);
            return;
        };
        
        for (int i = 0; i < N; i++) {
            // 맨 오른쪽 것 빼고 다 깨졌을 때
            if (k == N - 1 && i == N - 1) {
                func(k+1);
                continue;
            }
            
            if (rest[k] <= 0 || rest[i] <= 0 || i == k) continue;
            rest[k] -= arr[i][1];
            rest[i] -= arr[k][1];
            func(k+1);
            rest[k] += arr[i][1];
            rest[i] += arr[k][1];
        }
        
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        rest = new int[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            
            rest[i] = arr[i][0];
        }
        
        func(0);
        
        System.out.println(ans);

    }
}