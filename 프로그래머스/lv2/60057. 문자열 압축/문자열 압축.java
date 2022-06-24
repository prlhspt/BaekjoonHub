import java.util.*;

class Solution {

    public int solve(int n, int k, String s) {

        List<String> strs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n/k; i++) {
            strs.add(s.substring(i*k, i*k+k));
        }

        if (n%k != 0) {
            strs.add(s.substring(n / k * k, n));
        }

        for (int i = 0; i < strs.size() - 1; i++) {
            int count = 1;

            while (i < strs.size() - 1) {
                if (strs.get(i).equals(strs.get(i+1))) {
                    count++;
                    i++;
                } else {
                    break;
                }
            }

            if (count == 1) {
                sb.append(strs.get(i));
            } else {
                sb.append(count);
                sb.append(strs.get(i));
            }
        }

        if (!strs.get(strs.size() - 1).equals(strs.get(strs.size() - 2))) {
            sb.append(strs.get(strs.size() - 1));
        }

        return sb.length();

    }

    public int solution(String s) {

        if (s.length() == 1) {
            return 1;
        }

        int n = s.length();
        int answer = s.length();

        for (int i = 1; i <= s.length()/2; i++) {
            answer = Math.min(answer, solve(n, i, s));

        }

        return answer;
    }
}