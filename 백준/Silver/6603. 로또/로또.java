import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static int[] num;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
 
    public static void func(int k, int cur) {
        if (k == m) {
            for (int i = 0; i < k; i++)
                sb.append(num[arr[i]] + " ");
            sb.append("\n");
        }
        
        for (int i = cur; i < n; i++) {
            if (visited[i]) continue;
            
            arr[k] = i;
            visited[i] = true;
            func(k+1, i+1);
            visited[i] = false;
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        m = 6;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            
            num = new int[n];
            arr = new int[n];
            visited = new boolean[n];
            
            for (int i = 0; i < n; i++)
                num[i] = Integer.parseInt(st.nextToken());
            
            
            func(0, 0);
            sb.append("\n");
            
        }
        
        System.out.print(sb);      
  }
}