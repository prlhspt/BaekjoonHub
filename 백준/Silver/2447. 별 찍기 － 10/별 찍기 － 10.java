import java.io.*;

class Main {

  static int N;
  static int[][] star;

  public static void solve(int r, int c, int n) {
    if (n == 1) {
      star[r][c]++;
      return;
    }

    int d = n / 3;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == 1 && j == 1) continue;
        solve(r + i*d, c + j*d, d);
      }
    }
    
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    star = new int[N][N];

    solve(0, 0, N);

    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (star[i][j] == 1) {
          sb.append("*");
        } else {
          sb.append(" ");
        }
      }
      sb.append("\n");
    }

    System.out.println(sb.toString());
    
  }
}