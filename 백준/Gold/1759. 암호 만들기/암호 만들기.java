import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static String[] str;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    
    public static void func(int k, int cur, int con, int vow) {
        if (k == m) {
            if (con >= 2 && vow >= 1) {
                for (int i = 0; i < m; i++)
                    sb.append(str[arr[i]]);
                sb.append("\n");
            }
            return;
            
        };
        
        for (int i = cur; i < n; i++) {
            if (visited[i]) continue;
            
            arr[k] = i;
            
            visited[i] = true;
            
            if (str[i].equals("a") || str[i].equals("e") 
            || str[i].equals("i") || str[i].equals("o") || str[i].equals("u"))
                func(k+1, i+1, con, vow+1);
            else
                func(k+1, i+1, con+1, vow);
            visited[i] = false;
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] readString = br.readLine().split(" ");
        
        m = Integer.parseInt(readString[0]);
        n = Integer.parseInt(readString[1]);
        
        arr = new int[m];
        str = new String[n];
        visited = new boolean[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            str[i] = st.nextToken();
        }
        
        Arrays.sort(str);
        
        func(0, 0, 0, 0);
        
        System.out.print(sb);

  }
}