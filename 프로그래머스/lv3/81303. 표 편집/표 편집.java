import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    class Node {
        private int prev;
        private int next;
        private int curr;

        public int getPrev() {
            return prev;
        }

        public int getNext() {
            return next;
        }

        public int getCurr() {
            return curr;
        }

        public Node(int prev, int next, int curr) {
            this.prev = prev;
            this.next = next;
            this.curr = curr;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Deque<Node> deque = new ArrayDeque<>();

        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        StringBuilder sb = new StringBuilder("O".repeat(n));

        for (String s : cmd) {
            if (s.contains("U")) {
                int num = Integer.parseInt(s.split(" ")[1]);
                while (num-- > 0) {
                    k = prev[k];
                }
            } else if (s.contains("D")) {
                int num = Integer.parseInt(s.split(" ")[1]);
                while (num-- > 0) {
                    k = next[k];
                }
            } else if (s.contains("C")) {
                deque.offer(new Node(prev[k], next[k], k));
                sb.setCharAt(k, 'X');
                if (next[k] != -1) {
                    prev[next[k]] = prev[k];
                }

                if (prev[k] != -1) {
                    next[prev[k]] = next[k];
                }
                
                if (next[k] != -1) {
                    k = next[k];
                } else {
                    k = prev[k];
                }


            } else {
                Node now = deque.pollLast();
                sb.setCharAt(now.getCurr(), 'O');
                if (now.getPrev() != -1) {
                    next[now.getPrev()] = now.getCurr();
                }

                if (now.getNext() != -1) {
                    prev[now.getNext()] = now.getCurr();
                }
            }
        }

        return sb.toString();
    }
}