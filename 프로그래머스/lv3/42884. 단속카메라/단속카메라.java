/**
 end가 빠른 순서대로 설치하면 됨
 */

import java.util.*;

class Solution {

    public int solution(int[][] routes) {
        int answer = 0;
        int camera = -30001;
        
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        for (int[] route : routes) {
            if (camera < route[0]) {
                answer++;
                camera = route[1];
            }
        }
        
        return answer;
    }
}