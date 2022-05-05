import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static int[] res;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    public static void func(int k, int idx) {
        if (k == m) {
            for (int i = 0; i < m; i++)
                sb.append(res[i] + " ");
            sb.append("\n");
            return;
        }
        
        for (int i = idx; i < n; i++) {
            if (visited[i]) continue;
            
            res[k] = arr[i];
            
            visited[i] = true;
            func(k+1, i);
            visited[i] = false;
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        
        arr = new int[n];
        res = new int[n];
        visited = new boolean[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        func(0, 0);
        
        System.out.print(sb);

  }
}