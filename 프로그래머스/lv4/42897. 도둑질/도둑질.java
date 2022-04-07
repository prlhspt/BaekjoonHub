/*
결정 문제 : 심사에 걸리는 시간을 mid라고 했을 때 n명이 times안에 심사할 수 있는가?
Check(mid) = F -> F -> ... -> T -> T

Checklist
1. Check(lo) = F, Check(hi) = T를 만족하는가?
2. lo, hi는 정답이 될 수 있는 모든 범위를 나타낼 수 있는가?
 */

import java.math.BigInteger;
import java.util.*;

class Solution {

    boolean check(int[] arr, long mid, int target) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += (mid / arr[i]);
        }
        return sum >= target;
    }


    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long low = -1;
        long high = (long) n * (long) times[times.length - 1] + 1;

        // low와 high 사이에는 서로 다른 칸이 존재한다. (F -> T가 되는 경계)
        while (low + 1 < high) {
            long mid = low + (high - low) / 2;

            if (check(times, mid, n)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }
}
