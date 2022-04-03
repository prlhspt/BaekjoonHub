import java.util.*;

/**
 [얼굴, 상의, 하의, 겉옷]
 [옷 이름, 분류]

 - 같은 의상들은 하나씩만 꺼낼 수 있음
 - 완벽하게 겹치면 안됨
 */
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;

        Map<String, List<String>> map = new HashMap<>();

        for (String[] clothe : clothes) {
            map.computeIfAbsent(clothe[1], k -> new ArrayList<>()).add(clothe[0]);
            answer++;
        }

        if (map.size() > 1) {
            answer = 1;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                int size = entry.getValue().size();
                answer *= (size + 1);
            }
            answer -= 1;
        }

        return answer;
    }
}