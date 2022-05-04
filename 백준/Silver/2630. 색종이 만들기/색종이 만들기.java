import java.util.*;
import java.io.*;

class Main {

  static int N;
  static int[][] paper;
  static int[] answer;

  public static boolean check(int r, int c, int n) {
    for (int i = r; i < r + n; i++) {
      for (int j = c; j < c + n; j++) {
        if (paper[i][j] != paper[r][c])
          return false;
      }
    }
    return true;
  }

  public static void solve(int r, int c, int n) {
    if (check(r, c, n)) {
      answer[paper[r][c]]++;
      return;
    }

    int d = n / 2;

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        solve(r + i * d, c + j * d, d);
      }
    }
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    paper = new int[N][N];
    answer = new int[2];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        paper[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    solve(0, 0, N);

    for (int i = 0; i < 2; i++) {
      System.out.println(answer[i]);
    }
  }
}