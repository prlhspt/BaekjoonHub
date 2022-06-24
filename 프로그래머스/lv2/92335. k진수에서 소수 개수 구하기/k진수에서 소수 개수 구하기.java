import java.util.*;

class Solution {

    public boolean primeCheck(String s) {
        long num = Long.parseLong(s);

        if (num < 2) {
            return false;
        } else if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private boolean Extraction(Deque<String> deque, StringBuilder sb) {
        while (!deque.isEmpty() && !deque.peekLast().equals("0")) {
            sb.insert(0, deque.pollLast());
        }

        if (!sb.toString().equals("") && primeCheck(sb.toString())) {
            return true;
        }
        return false;
    }

    public int solution(int n, int k) {

        int answer = 0;

        String notation = Integer.toString(n, k);

        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < notation.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (notation.charAt(i) == '0') {
                if (!deque.isEmpty() && Extraction(deque, sb)) {
                    answer++;
                }
            }
            deque.offer(String.valueOf(notation.charAt(i)));
        }

        StringBuilder sb = new StringBuilder();
        if (!deque.isEmpty() && Extraction(deque, sb)) {
            answer++;
        }

        return answer;
    }
}