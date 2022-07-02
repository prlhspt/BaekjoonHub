import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    static class Node {
        private int index;

        private Node prev;
        private Node next;

        public Node(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        boolean hasNext() {
            return next.index != -1;
        }

        public void restore() {
            prev.next = this;
            next.prev = this;
        }

        public void remove() {
            prev.next = next;
            next.prev = prev;
        }

        private static Node initList(int n) {
            Node start = new Node(-1);
            Node prev = start;
            Node curr = null;
            for (int i = 0; i < n; i++) {
                curr = new Node(i);
                prev.next = curr;
                curr.prev = prev;
                prev = curr;
            }
            curr.next = new Node(-1);
            return start.next;
        }
    }


    public String solution(int n, int k, String[] cmd) {
        Node curr = Node.initList(n);
        Deque<Node> deque = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            curr = curr.getNext();
        }

        for (String s : cmd) {
            if (s.contains("U")) {
                int distance = getDistance(s);
                while (distance-- > 0) {
                    curr = curr.getPrev();
                }
            } else if (s.contains("D")) {
                int distance = getDistance(s);
                while (distance-- > 0) {
                    curr = curr.getNext();
                }
            } else if (s.contains("C")) {
                deque.offer(curr);
                curr.remove();
                curr = curr.hasNext() ? curr.getNext() : curr.getPrev();
            } else {
                deque.pollLast().restore();
            }
        }

        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!deque.isEmpty()) {
            sb.setCharAt(deque.pollLast().getIndex(), 'X');
        }

        return sb.toString();
    }

    private int getDistance(String s) {
        return Integer.parseInt(s.split(" ")[1]);
    }

}
