import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static int[] num;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    public static void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++)
                sb.append(arr[i] + " ");
            sb.append("\n");
            return;
        }
        
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || tmp == num[i]) continue;
            
            arr[k] = num[i];
            tmp = arr[k];
            
            visited[i] = true;
            func(k+1);
            visited[i] = false;
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] readString = br.readLine().split(" ");
        
        n = Integer.parseInt(readString[0]);
        m = Integer.parseInt(readString[1]);
        
        arr = new int[n];
        num = new int[n];
        visited = new boolean[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(num);
        
        func(0);
        
        System.out.println(sb);

  }
}