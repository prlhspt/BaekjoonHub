import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr = new int[10];
    static int[] num = new int[10];
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    public static void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++)
                sb.append(num[arr[i]] + " ");
            sb.append("\n");
            return;
        }
        
        for (int i = 0; i < n; i++) {
            arr[k] = i;
            func(k+1);
        }
    }
    
    static boolean[] chk = new boolean[10002];
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] readString = br.readLine().split(" ");
        
        n = Integer.parseInt(readString[0]);
        m = Integer.parseInt(readString[1]);
    
        num = new int[n];
        visited = new boolean[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int idx = 0;
        while ((n--) > 0) {
            num[idx] = Integer.parseInt(st.nextToken());
            if (chk[num[idx]]) continue;
            chk[num[idx]] = true;
            idx++;
        }
        
        // System.out.println(Arrays.toString(num));
        
        n = idx;
        Arrays.sort(num, 0, 0+n);
        func(0);
        
        System.out.println(sb);

  }
}