import java.util.*;
import java.util.stream.*;

/**
 전체 학생 수 n, 도난당한 학생 lost, 여벌의 체육복 reverse

 */

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] u = new int[n + 2];

        Arrays.sort(lost);
        Arrays.sort(reserve);

        for (int i = 0; i <= n + 1; i++) {
            u[i] = 1;
        }

        for (int l : lost) {
            u[l] -= 1;
        }

        for (int r : reserve) {
            u[r] += 1;
        }

        for (int i = 1; i <= n; i++) {
            if (u[i - 1] == 0 && u[i] == 2) {
                u[i - 1] = 1;
                u[i] = 1;
            } else if (u[i] == 2 && u[i + 1] == 0) {
                u[i] = 1;
                u[i + 1] = 1;
            }
        }

        return Math.toIntExact(IntStream.range(1, n + 1)
                .filter(i -> u[i] > 0)
                .count());
    }
}