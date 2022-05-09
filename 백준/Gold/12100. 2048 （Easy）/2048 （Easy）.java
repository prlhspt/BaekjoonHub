import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int ans = 0;

    static int[][] board;

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    static int[][] deepCopy(int[][] arr) {
        int[][] res = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return res;
    }

    static boolean OOB(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    // 우선 0 없을때까지 당기기
    // 향하는 방향에 같은 숫자가 있으면 합치기 (2개씩)
    // r쪽이면 위아래 같은 수 비교하면 될 듯
    static void move(int dir) {
        // 위
        if (dir == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[j][i] == 0) {
                        int tmp = j;
                        while (true) {
                            tmp++;
                            if (OOB(tmp, i)) break;

                            if (board[tmp][i] != 0) {
                                board[j][i] = board[tmp][i];
                                board[tmp][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }
            // 합치기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (board[j][i] == board[j+1][i]) {
                        board[j][i] *= 2;
                        board[j+1][i] = 0;

                        int tmp = j;
                        while (true) {
                            tmp++;
                            if (OOB(tmp+1, i)) break;

                            board[tmp][i] = board[tmp+1][i];
                            board[tmp+1][i] = 0;

                        }
                    }
                }
            }
            // 아래
        } else if (dir == 2) {
            for (int i = n-1; i >= 0; i--) {
                for (int j = n-1; j >= 0; j--) {
                    if (board[j][i] == 0) {
                        int tmp = j;
                        while (true) {
                            tmp--;
                            if (OOB(tmp, i)) break;

                            if (board[tmp][i] != 0) {
                                board[j][i] = board[tmp][i];
                                board[tmp][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }
            // 합치기
            for (int i = n-1; i >= 0; i--) {
                for (int j = n-1; j >= 1; j--) {
                    if (board[j][i] == board[j-1][i]) {
                        board[j][i] *= 2;
                        board[j-1][i] = 0;

                        int tmp = j;
                        while (true) {
                            tmp--;
                            if (OOB(tmp-1, i)) break;

                            board[tmp][i] = board[tmp-1][i];
                            board[tmp-1][i] = 0;

                        }
                    }
                }
            }
            // 오른쪽
        } else if (dir == 1) {
            for (int i = n-1; i >= 0; i--) {
                for (int j = n-1; j >= 0; j--) {
                    if (board[i][j] == 0) {
                        int tmp = j;
                        while (true) {
                            tmp--;
                            if (OOB(i, tmp)) break;

                            if (board[i][tmp] != 0) {
                                board[i][j] = board[i][tmp];
                                board[i][tmp] = 0;
                                break;
                            }
                        }
                    }
                }
            }
            // 합치기
            for (int i = n-1; i >= 0; i--) {
                for (int j = n-1; j >= 1; j--) {
                    if (board[i][j] == board[i][j-1]) {
                        board[i][j] *= 2;
                        board[i][j-1] = 0;

                        int tmp = j;
                        while (true) {
                            tmp--;
                            if (OOB(i, tmp-1)) break;

                            board[i][tmp] = board[i][tmp-1];
                            board[i][tmp-1] = 0;

                        }
                    }
                }
            }
            // 왼쪽
        } else if (dir == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        int tmp = j;
                        while (true) {
                            tmp++;
                            if (OOB(i, tmp)) break;

                            if (board[i][tmp] != 0) {
                                board[i][j] = board[i][tmp];
                                board[i][tmp] = 0;
                                break;
                            }
                        }
                    }
                }
            }
            // 합치기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (board[i][j] == board[i][j+1]) {
                        board[i][j] *= 2;
                        board[i][j+1] = 0;

                        int tmp = j;
                        while (true) {
                            tmp++;
                            if (OOB(i, tmp+1)) break;

                            board[i][tmp] = board[i][tmp+1];
                            board[i][tmp+1] = 0;

                        }
                    }
                }
            }
        }
    }

    static void solve(int k) {
        if (k == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(board[i][j], ans);
                }
            }
            return;
        }

        // 4 방향으로 백트래킹 하면 될 듯?
        for (int i = 0; i < 4; i++) {
            int[][] copy = deepCopy(board);

            move(i);

            solve(k+1);

            board = copy;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0);
        
        System.out.print(ans);

    }
}