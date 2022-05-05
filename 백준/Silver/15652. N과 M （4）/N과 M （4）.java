import java.util.*;

public class Main {

  static int n, m;
  static int[] arr;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  public static void func(int k, int cur) {
      if (k == m) {
          for (int i = 0; i < k; i++) {
            sb.append(arr[i] + " ");
          }
          sb.append("\n");
          return;
      }
      
      for (int i = cur; i < n; i++) {
          arr[k] = i+1;
          func(k+1, i);
      }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    visited = new boolean[n];
    arr = new int[n];

    func(0, 0);
    
    System.out.print(sb);
    
  }
}