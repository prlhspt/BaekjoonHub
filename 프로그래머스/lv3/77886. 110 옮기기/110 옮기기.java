import java.util.*;

class Solution {

    String solve(String str) {

        int count = 0;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= 3) {
                // 1 1 0
                char c1 = stack.pop();
                if (c1 != '0') {
                    stack.push(c1);
                    continue;
                }
                char c2 = stack.pop();
                if (c2 != '1') {
                    stack.push(c1);
                    stack.push(c2);
                    continue;
                }
                char c3 = stack.pop();
                if (c3 != '1') {
                    stack.push(c1);
                    stack.push(c2);
                    stack.push(c3);
                    continue;
                }
                count++;
            }

        }

        StringBuilder sb = new StringBuilder();

        int zeroIndex = stack.size();
        boolean zeroFlag = false;

        // 전체가 1이면 맨 왼쪽에 다 붙일 거임
        // 0이 섞여있으면 처음 0 에 인덱스 맞춰놓고 sb.insert 할꺼임
        // 0 1 110
        /*
        01101
        01110
        11001

         */

        while (!stack.isEmpty()) {
            char c = stack.pop();

            // 0을 만날때까지 계속 -1 하다가 0만나면 멈춰, 끝까지 1이면 맨 왼쪽에 붙이면 되고, 0 만나면 그냥 거기에 붙여주면 됨
            if (c == '0') {
                zeroFlag = true;
            } else if (c == '1' && !zeroFlag) {
                zeroIndex--;
            }
            sb.insert(0, c);
        }


        for (int i = 0; i < count; i++) {
            sb.insert(zeroIndex, "110");
        }

        return sb.toString();

    }

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            answer[i] = solve(s[i]);
        }

        return answer;
    }
}