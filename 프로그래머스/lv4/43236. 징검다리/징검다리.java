/*
결정 문제 : 심사에 걸리는 시간을 mid라고 했을 때 n명이 times안에 심사할 수 있는가?
Check(mid) = T -> T -> ... -> F -> F

Checklist
1. Check(lo) = F, Check(hi) = T를 만족하는가?
2. lo, hi는 정답이 될 수 있는 모든 범위를 나타낼 수 있는가?
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

        // System.out.println(Arrays.toString(disArr));

        long low = -1;
        long high = distance + 1;

        // [2, 11, 14, 17, 21]
        // [2, 9, 3, 3, 4, 4]
        // [25]
        // 13일때 2, 9 지우고 오른쪽 3에 합치면 13
        // 4, 4 지우고 왼쪽 3에 합치면 11

        while (low + 1 < high) {
            long mid = low + (high - low) / 2;

            // System.out.println("mid : " + mid);
            
            long sum = 0;
            long remove = 0;
            for (int i = 0; i < disArr.length; i++) {
                sum += disArr[i];
                // System.out.println("sum : " + sum);
                // System.out.println("remove : " + remove);

                if (sum >= mid) {
                    sum = 0;
                    continue;
                }
                remove++;
            }
                // System.out.println("remove : " + remove);
            
            if (remove > n) {
                high = mid;
            } else if (remove <= n) {
                low = mid;
            }
            // System.out.println("low : " + low);
            // System.out.println("high : " + high);
        }

        // System.out.print(high);

        return (int) low;
    }
}