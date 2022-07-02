import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String s) {

        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');
        
        int answer = 0;

        Deque<Character> deque = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            deque.offer(c);
        }
        
        int length = s.length();
        for (int i = 0; i < length; i++) {
            Character poll = deque.poll();
            deque.offer(poll);
            
            Deque<Character> copyDeque = new ArrayDeque<>(deque);
            Deque<Character> pairCheckDeque = new ArrayDeque<>();

            while (!copyDeque.isEmpty()) {
                Character now = copyDeque.poll();

                if (pairCheckDeque.peekLast() != null && map.get(pairCheckDeque.peekLast()) == now) {
                    pairCheckDeque.pollLast();
                } else {
                    pairCheckDeque.offer(now);
                }
            }
            if (pairCheckDeque.size() == 0) {
                answer++;
            }

        }

        return answer;
    }

}