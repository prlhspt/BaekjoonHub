import java.util.*;

public class Main {

  static int n, m;
  static int[] arr;
  static StringBuilder sb = new StringBuilder();

  public static void func(int k) {
    if (k == m) {
      for (int i = 0; i < k; i++) {
          sb.append(arr[i] + " ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < n; i++) {
      arr[k] = i+1;
      func(k+1);
    }
    
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();

    arr = new int[m];

    func(0);
    System.out.println(sb);
    
  }
}