import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int length = name.length();
        
        // 앞에서부터 순서대로 다 탐색하는것을 기본값으로 설정
        int minMove = length - 1;
        
        for (int i = 0; i < length; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            int next = i + 1;
            
            // A는 바꿀게 없으므로 뒤로 가는게 더 빠를수 있음 
            // next로 인덱스 잡고, 앞으로 가다가 뒤로 가는 경우랑 비교할 것
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            // 앞으로 i만큼 가다가 다시 뒤로 돌아가는거랑 비교
            minMove = Math.min(minMove, i*2 + length - next);
            
            // 시작하마자 뒤로 간다음에 다시 앞으로 가는거랑 비교
            minMove = Math.min(minMove, (length - next) * 2 + i);
            
        }
        return minMove + answer;
    }
}