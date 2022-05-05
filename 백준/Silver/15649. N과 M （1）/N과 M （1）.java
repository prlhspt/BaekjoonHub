import java.util.*;

class Main {

  static int n, m;
  static int[] arr;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void solve(int k) {
    if (k == m) {
      for (int i = 0; i < k; i++) {
        sb.append(arr[i] + " ");
      }
      sb.append("\n");
    }

    for (int i = 1; i <= n; i++) {
      if (visited[i]) continue;
      
      visited[i] = true;
      arr[k] = i;
      solve(k+1);
      visited[i] = false;
    }
    
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();

    arr = new int[n];
    visited = new boolean[n+1];

    solve(0);

    System.out.print(sb);
  }
}