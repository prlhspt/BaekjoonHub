import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> table = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            table.add(i);
        }

        for (String s : cmd) {
            if (s.contains("U")) {
                String[] split = s.split(" ");
                int num = Integer.parseInt(split[1]);
                k -= num;
            } else if (s.contains("D")) {
                String[] split = s.split(" ");
                int num = Integer.parseInt(split[1]);
                k += num;
            } else if (s.contains("C")) {
                int remove = table.remove(k);
                deque.offer(remove);
                if (k == table.size() - 1) {
                    k--;
                }
            } else {
                int num = deque.pollLast();
                if (num >= table.size()) {
                    table.add(num);
                } else {
                    table.add(num, 1);
                }
            }
        }

        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = "O";
        }

        for (int i : deque) {
            answer[i] = "X";
        }

        return String.join("", answer);
    }
}