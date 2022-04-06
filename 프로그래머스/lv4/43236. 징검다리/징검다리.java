/*
결정 문제 : 바위 사이의 거리들 중 최소 거리를 mid으로 뒀을 때 돌을 n개만 치울 수 있는가?
Check(mid) = F -> F -> ... -> T -> T -> ... -> F -> F
구하는 답은 mid(바위 거리들 중 최소 거리)의 최댓값이므로 T에서 F로 변하는 경계까지 가서 T를 선택하면 된다.

Checklist
1. Check(lo) = T, Check(hi) = F를 만족하는가?
2. lo, hi는 정답이 될 수 있는 모든 범위를 나타낼 수 있는가?

* 바위의 거리 배열에서 바위를 지우면 그 오른쪽의 거리에 더해진다.
*/

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks);

        int[] disArr = new int[rocks.length + 1];

        // 바위 거리
        disArr[0] = rocks[0];
        for (int i = 1; i < disArr.length - 1; i++) {
            disArr[i] = rocks[i] - rocks[i - 1];
        }
        disArr[disArr.length - 1] = distance - rocks[rocks.length - 1];

        long low = -1;
        long high = distance + 1;

        while (low + 1 < high) {
            long mid = low + (high - low) / 2;

            long sum = 0;
            long remove = 0;
            for (int i = 0; i < disArr.length; i++) {
                sum += disArr[i];

                if (sum >= mid) {
                    sum = 0;
                    continue;
                }
                remove++;
            }
            
            if (remove > n) {
                high = mid;
            } else if (remove <= n) {
                low = mid;
            }
        }

        return (int) low;
    }
}