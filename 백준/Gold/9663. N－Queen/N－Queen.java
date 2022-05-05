import java.util.*;

class Main {

  static int n;
  static int count = 0;
  
  static boolean[] visited1;
  static boolean[] visited2;
  static boolean[] visited3;

  public static void solve(int x) {
    if (x == n) {
      count++;
      return;
    }

    for (int y = 0; y < n; y++) {
      if (visited1[y]) continue;
      if (visited2[x+y]) continue;
      if (visited3[x-y+n-1]) continue;

      visited1[y] = true;
      visited2[x+y] = true;
      visited3[x-y+n-1] = true;
      
      solve(x+1);
      
      visited1[y] = false;
      visited2[x+y] = false;
      visited3[x-y+n-1] = false;
      
    }
    
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();

    visited1 = new boolean[n];
    visited2 = new boolean[n*2-1];
    visited3 = new boolean[n*2-1];

    solve(0);

    System.out.println(count);
    
  }
}