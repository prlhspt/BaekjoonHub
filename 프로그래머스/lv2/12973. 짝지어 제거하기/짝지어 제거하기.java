import java.util.ArrayDeque;
import java.util.Deque;

class Solution
{
    public int solution(String s)
    {
        Deque<Character> deque = new ArrayDeque<>();


        for (char c : s.toCharArray()) {
            if (deque.peekLast() != null && deque.peekLast() == c) {
                deque.pollLast();
            } else {
                deque.offer(c);
            }
        }

        if (deque.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}