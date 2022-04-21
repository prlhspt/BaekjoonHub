import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {

        int answer = 0;

        PriorityQueue<Integer> pqA = new PriorityQueue<>();
        PriorityQueue<Integer> pqB = new PriorityQueue<>();

        for (int i = 0; i < A.length; i ++) {
            pqA.offer(A[i]);
            pqB.offer(B[i]);
        }

/*
1 3 5 7

2 2 6 8
*/
        while (!pqA.isEmpty() && !pqB.isEmpty()) {
            int pollA = pqA.poll();

            while (!pqB.isEmpty()) {
                int pollB = pqB.poll();

                if (pollA < pollB) {
                    answer++;
                    break;
                }
            }

        }

        return answer;
    }
}