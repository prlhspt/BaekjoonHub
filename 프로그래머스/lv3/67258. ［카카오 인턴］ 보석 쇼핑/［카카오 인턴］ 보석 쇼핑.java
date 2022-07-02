import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        int[] answer = new int[2];
        answer[0] = 987654321;
        int count = 987654321;

        Map<String, Integer> map = new HashMap<>();
        Set<String> gemSet = new HashSet<>();

        for (String s : gems) {
            gemSet.add(s);
        }

        int index = -1;
        for (int i = 0; i < gems.length; i++) {
            while (++index < gems.length && map.size() != gemSet.size()) {
                int finalIndex = index;
                map.merge(gems[index], 1, (ov, nv) -> map.get(gems[finalIndex]) + 1);
            }
            index--;

            int distance = index - i;
            if (distance < count && map.size() == gemSet.size()) {
                count = distance;
                answer[0] = i + 1;
                answer[1] = index + 1;
            }

            map.merge(gems[i], 0, (ov, nv) -> {
                if (ov <= 1) {
                    return null;
                } else {
                    return ov - 1;
                }
            });
        }

        return answer;
    }
}