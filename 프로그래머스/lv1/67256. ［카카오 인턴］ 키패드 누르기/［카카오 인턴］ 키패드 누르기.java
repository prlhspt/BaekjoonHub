import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import static java.util.Map.entry;

class Solution {

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int left = 11;
    int right = 12;

    Map<Integer, Node> map = Map.ofEntries(entry(1, new Node(0, 0)), entry(2, new Node(0, 1)),
            entry(3, new Node(0, 2)), entry(4, new Node(1, 0)), entry(5, new Node(1, 1)),
            entry(6, new Node(1, 2)), entry(7, new Node(2, 0)), entry(8, new Node(2, 1)),
            entry(9, new Node(2, 2)), entry(0, new Node(3, 1)), entry(11, new Node(3, 0)), entry(12, new Node(3, 2)));

    public void pushCheck(int number, String hand, StringBuilder sb) {

        Node arrive = map.get(number);

        Node leftPos = map.get(left);
        Node rightPos = map.get(right);

        int leftDistance = Math.abs(arrive.x - leftPos.x) + Math.abs(arrive.y - leftPos.y);
        int rightDistance = Math.abs(arrive.x - rightPos.x) + Math.abs(arrive.y - rightPos.y);


        if (leftDistance < rightDistance) {
            sb.append("L");
            left = number;

        } else if (leftDistance > rightDistance) {
            sb.append("R");
            right = number;

        } else {
            sb.append(hand);
            if (hand.equals("L")) {
                left = number;
            } else {
                right = number;
            }
        }
    }

    public String solution(int[] numbers, String hand) {

        StringBuilder sb = new StringBuilder();

        if (hand.equals("left")) {
            hand = "L";
        } else {
            hand = "R";
        }

        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                left = number;
                sb.append("L");
            } else if (number == 3 || number == 6 || number == 9) {
                right = number;
                sb.append("R");
            } else {
                pushCheck(number, hand, sb);
            }
        }

        return sb.toString();
    }
}