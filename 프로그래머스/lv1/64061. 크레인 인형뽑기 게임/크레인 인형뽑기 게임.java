import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int move : moves) {
            int index = -1;
            while (++index < board.length) {
                if (board[index][move - 1] != 0) {
                    int item = board[index][move - 1];
                    if (deque.peekLast() != null && deque.peekLast() == item) {
                        deque.pollLast();
                        answer+=2;
                    } else {
                        deque.offer(item);
                    }
                    board[index][move - 1] = 0;
                    break;
                }
            }
        }

        return answer;
    }
}