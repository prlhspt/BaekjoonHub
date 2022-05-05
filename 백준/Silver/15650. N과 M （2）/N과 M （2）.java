import java.util.*;

class Main {

  static int n;
  static int[] arr;
  static boolean[] visited;

  public static void func(int cur, int r) {
    if (r == 0) {
      for (int i = 0; i < n; i++) {
        if (visited[i]) System.out.print(i+1 + " ");
      }
      System.out.println();
      return;
    }

    for (int i = cur; i < n; i++) {
      if (visited[i]) continue;

      visited[i] = true;
      func(i, r-1);
      visited[i] = false;
      
    }
    
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    
    int r = sc.nextInt();

    visited = new boolean[n];

    func(0, r);
    
  }
}