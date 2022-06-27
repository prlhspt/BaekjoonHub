import java.util.*;
import java.util.regex.Pattern;

class Solution {


    boolean[] VIS;
    long ANSWER;

    public long solve(String expression, String[] pick) {

        StringBuilder sb = new StringBuilder(expression);

        // pick 우선순위대로 연산
        for (String s : pick) {
            while (sb.indexOf(s) != -1) {
                int index = sb.indexOf(s);

                int left = index;
                while (--left > 0) {
                    if (sb.charAt(left) == '*' || sb.charAt(left) == '+' ||
                            sb.charAt(left) == '$'){
                        left++;
                        break;
                    }
                }

                int right = index;
                while (++right < sb.length()) {
                    if (sb.charAt(right) == '*' || sb.charAt(right) == '+' ||
                            sb.charAt(right) == '$') {
                        break;
                    }
                }

                String substring1 = sb.substring(left, index);
                String substring2 = sb.substring(index + 1, right);

                long insert;
                if (s == "$") {
                    insert = Long.parseLong(substring1) - Long.parseLong(substring2);
                } else if (s == "*") {
                    insert = Long.parseLong(substring1) * Long.parseLong(substring2);
                } else {
                    insert = Long.parseLong(substring1) + Long.parseLong(substring2);
                }

                sb.delete(left, right);
                sb.insert(left, insert);
            }
        }

        return Long.parseLong(sb.toString());
    }

    public void permOp(int k, List<String> op, String expression, String[] pick) {
        if (k == op.size()) {
            ANSWER = Math.max(ANSWER, Math.abs(solve(expression, pick)));
            return;
        }

        for (int i = 0; i < op.size(); i++) {
            if (!VIS[i]) {
                VIS[i] = true;
                pick[k] = op.get(i);
                permOp(k + 1, op, expression, pick);
                VIS[i] = false;
            }
        }

    }

    public long solution(String expression) {
        List<String> op = new ArrayList<>();

        expression = expression.replace("-", "$");
        Set<String> set = Set.of("+", "*", "$");

        for (String s : set) {
            if (expression.contains(s)) {
                op.add(s);
            }
        }

        VIS = new boolean[op.size()];
        String[] pick = new String[op.size()];

        permOp(0, op, expression, pick);


        return ANSWER;
    }
}