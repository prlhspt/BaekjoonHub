// 10억번이면 N에 풀기에도 어려우므로 이분탐색 고려

/*
시작 시 바로 2명 출발
7분 됐을 때 1명 출발
10분 됐을 때 1명 출발
14분 됐을 때 1명 출발
21분 됐을 때 1명 출발(20분 아님)
28분에 끝

어떻게 n이랑 times만 가지고 28을 이분탐색?

10억을 이분탐색 야무지게 잘라서 조건 탐색하는 방법 없을까?

10억과 무언가를 비교해서 무언가 < 10억 이면 
무언가 < 10억/2 가 돼야 하는데,,
*/

/**
힌트
처음에 middle 값을 대충 정함
middle 시간안에 처리할 수 있는 총 사람수를 구함
[해당시간에 처리할수 있는 사람수]와 [목표 사람수]를 비교함
*/

/**
입국 심사를 기다리는 사람
n < 1,000,000,000

심사관이 심사하는데 걸리는 시간 배열
times.length < 100,000
*/

import java.util.*;

class Solution {

    public long solution(int n, int[] times) {

        long answer = 0;
        
        Arrays.sort(times);
        
        long left = 0;
        long right = (long) n * times[times.length - 1];
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            long people = 0;
            for (int i = 0; i < times.length; i++) {
                people += (mid / times[i]);
            }
            
            if (n > people) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}