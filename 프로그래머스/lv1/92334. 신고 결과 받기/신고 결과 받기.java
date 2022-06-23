/*
메일을 
1. 내가 신고당한 유저 리스트를 가지고 있기(a가 신고한 유저고, b가 신고당한 유저임)
2. 유저 리스트가 2 넘으면 나 정지당했다고 메일 보내고 신고당한 유저리스트들한테 메일 다 뿌리기
*/

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, Integer> idMap = new LinkedHashMap<>();
        Map<String, Set<String>> map = new HashMap<>();
        
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new HashSet<>());
            idMap.put(id_list[i], 0);
        }
        
        for (int i = 0; i < report.length; i++) {
            String a = report[i].split(" ")[0];
            String b = report[i].split(" ")[1];
            map.get(b).add(a);
        }
        
        // 맵에 <신고당한 사람, 신고한사람> 들어있음
        // 신고한사람이 2 넘으면 신고한 사람들한테 메일 +1씩 해주기
        
        for (Map.Entry<String, Set<String>> entrySet : map.entrySet()) {
            if (entrySet.getValue().size() >= k) {
                
                Set<String> values = entrySet.getValue();
                
                for (String v : values) {
                    idMap.put(v, idMap.get(v) + 1);
                }
                
            }
        }
        
        int[] answer = new int[id_list.length];
        
        int index = 0;
        for (Integer v : idMap.values()) {
            answer[index] = v;
            index++;
        }
        
        return answer;
    }
}