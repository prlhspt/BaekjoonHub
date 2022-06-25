import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    int N;
    String[] NUMS;
    int ANSWER;

    int[] PICK;
    Set<Long> set = new HashSet<>();
    boolean[] VISITED;

    public void primeCheck(int r) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(NUMS[PICK[i]]);
        }

        long num = Integer.parseInt(sb.toString());

        if (set.contains(num)) {
            return;
        }

        set.add(num);

        if (num < 2) {
            return;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return;
            }
        }
        ANSWER++;
    }



    public void perm(int k, int r) {
        if (k == r) {
            primeCheck(r);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (VISITED[i]) continue;
            VISITED[i] = true;
            PICK[k] = i;
            perm(k+1, r);
            VISITED[i] = false;
            PICK[k] = 0;
        }
    }

    public int solution(String numbers) {

        N = numbers.length();
        NUMS = new String[N];
        VISITED = new boolean[N];
        PICK = new int[N];

        char[] chars = numbers.toCharArray();
        for (int i = 0; i < N; i++) {
            NUMS[i] = String.valueOf(chars[i] - '0');
        }

        for (int i = 0; i < N; i++) {
            perm(0, i+1);
        }

        return ANSWER;
    }
}