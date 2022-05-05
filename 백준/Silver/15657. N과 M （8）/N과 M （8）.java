import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static int[] num;
    static StringBuilder sb = new StringBuilder();
    
    public static void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++)
                sb.append(num[arr[i]] + " ");
            sb.append("\n");
            return;
        }
        
        int st = 0;
        if (k != 0) st = arr[k-1];
        for (int i = st; i < n; i++) {
            
            arr[k] = i;
            func(k+1);
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] s = br.readLine().split(" ");
        
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        
        arr = new int[n];
        num = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(num);
        
        func(0);
        
        System.out.print(sb);

  }
}