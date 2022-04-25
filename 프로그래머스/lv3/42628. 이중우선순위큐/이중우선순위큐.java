import java.util.*;

/**
 * 최소 힙이랑 최대 힙 둘다 만들어서 동시에 관리하기
 */

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> minQueue = new PriorityQueue<>();

        for (int i = 0; i < operations.length; i++) {
            String[] s = operations[i].split(" ");

            // 힙에 넣기
            if (s[0].equals("I")) {
                maxQueue.offer(Integer.parseInt(s[1]));
                minQueue.offer(Integer.parseInt(s[1]));

            } else {
                // 최댓값 제거
                if (s[1].equals("1")) {
                    if (!maxQueue.isEmpty()) {
                        int max = maxQueue.poll();
                        minQueue.remove(max);
                    }

                    // 최솟값 제거
                } else {
                    if (!minQueue.isEmpty()) {
                        int min = minQueue.poll();
                        maxQueue.remove(min);
                    }
                }
            }
        }
        
        if (maxQueue.size() > 1) {
            answer[0] = maxQueue.poll();
            answer[1] = minQueue.poll();    
        }

        return answer;
    }
}