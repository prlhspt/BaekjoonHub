import java.util.*;

class Solution {

    public String[] solution(String[][] tickets) {

        Map<String, List<String>> map = new HashMap<>();
        Deque<String> deque = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tickets.length; i++) {
            map.computeIfAbsent(tickets[i][0],
                    k -> new ArrayList()).add(tickets[i][1]);
        }

        int length = map.size();

        for (int i = 0; i < length; i++) {
            for (List<String> l : map.values()) {
                Collections.sort(l);
            }
        }

        String start = "ICN";

        deque.offer(start);

        while (!deque.isEmpty()) {
            String top = deque.peekLast();
            List<String> topList = map.get(top);
            if (topList == null || topList.size() == 0) {
                result.add(deque.pollLast());
            } else {
                deque.offer(topList.remove(0));
            }
        }

        Collections.reverse(result);

        return result.stream().toArray(s -> new String[s]);
    }
}