import java.util.*;
import java.math.BigInteger;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n + 1][m + 1];

        if (puddles[0].length > 0) {
            for (int[] puddle : puddles) {
                arr[puddle[1]][puddle[0]] = -1;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i][1] == -1) {
                break;
            }
            arr[i][1] = 1;
        }

        for (int i = 1; i <= m; i++) {
            if (arr[1][i] == -1) {
                break;
            }
            arr[1][i] = 1;
        }

        for (int j = 2; j <= m; j++) {
            for (int i = 2; i <= n; i++) {
                // 바꾸려는게 -1
                if (arr[i][j] != -1) {
                    int a = 0;
                    int b = 0;
                    if (arr[i-1][j] > 0) {
                        a = arr[i-1][j] % 1000000007;
                    }
                    
                    if (arr[i][j-1] > 0) {
                        b = arr[i][j-1] % 1000000007;
                    }
                    arr[i][j] = (a + b) % 1000000007;
                }
            }
        }
        return arr[n][m];
    }
}