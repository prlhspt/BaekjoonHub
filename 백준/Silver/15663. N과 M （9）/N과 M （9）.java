import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m;
    static int[] arr;
    static int[] num;
    static boolean[] visited;
    static Set<String> set;
    
    public static void func(int k, int cur) {
        if (k == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++)
                sb.append(num[arr[i]] + " ");
            set.add(sb.toString().trim());
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            arr[k] = i;
            visited[i] = true;
            func(k+1, i);
            visited[i] = false;
            
        }
    }
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] readString = br.readLine().split(" ");
        
        n = Integer.parseInt(readString[0]);
        m = Integer.parseInt(readString[1]);
        
        set = new LinkedHashSet<>();
        
        arr = new int[n];
        num = new int[n];
        visited = new boolean[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(num);
        
        func(0, 0);
        
        Iterator<String> it = set.iterator();
        
        StringBuilder sb = new StringBuilder();
        
        while (it.hasNext()) {
            String s = it.next();
            st = new StringTokenizer(s, " ");
            int len = st.countTokens();
            for (int i = 0; i < len; i++) {
                sb.append(st.nextToken() + " ");
            }
            sb.append("\n");
            
        }
        System.out.println(sb);

  }
}