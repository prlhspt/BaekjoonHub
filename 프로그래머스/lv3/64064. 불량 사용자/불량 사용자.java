import java.util.*;

class Solution {

    int ANSWER;

    public void check(String[] pick, String[] user_id, Set<List<String>> duplicateCheck) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < pick.length; i++) {
            list.add(pick[i]);
            char[] userChar = user_id[i].toCharArray();
            char[] pickChar = pick[i].toCharArray();

            if (userChar.length != pickChar.length) {
                return;
            }

            for (int j = 0; j < userChar.length; j++) {
                if (userChar[j] == '*') {
                    continue;
                }

                if (userChar[j] != pickChar[j]) {
                    return;
                }
            }
        }

        Collections.sort(list);

        if (duplicateCheck.add(list)) {
            ANSWER++;
        }
    }

    public void permutation(int k, int n, int r, String[] pick, String[] user_id,
                            String[] banned_id, boolean[] visited, Set<List<String>> duplicateCheck) {
        if (k == r) {
            check(pick, banned_id, duplicateCheck);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            pick[k] = user_id[i];
            permutation(k+1, n, r, pick, user_id, banned_id, visited, duplicateCheck);
            visited[i] = false;
            pick[k] = user_id[i];

        }

    }

    public int solution(String[] user_id, String[] banned_id) {
        int n = user_id.length;
        int r = banned_id.length;

        boolean[] visited = new boolean[n];
        String[] pick = new String[r];
        Set<List<String>> duplicateCheck = new HashSet<>();

        permutation(0, n, r, pick, user_id, banned_id, visited, duplicateCheck);

        return ANSWER;
    }
}