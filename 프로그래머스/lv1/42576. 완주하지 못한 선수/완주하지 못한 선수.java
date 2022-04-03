import java.util.*;

/*
참여자: participant
완주자: completion
answer: 완주 못한 사람
동명이인 있음
*/
class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();

        for (String part : participant) {
            map.merge(part, 1, Integer::sum);
        }

        for (String comp : completion) {
            map.merge(comp, -1, Integer::sum);
        }

        return map.entrySet().stream()
                .filter(h -> h.getValue() == 1)
                .findFirst()
                .get().getKey();
    }
}