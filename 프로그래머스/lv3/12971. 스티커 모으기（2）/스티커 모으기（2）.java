/*
다이나믹 프로그래밍
[14, 6, 5, 11, 3, 9, 2, 10]
이웃한건 못뺌
연속 안되게 선택해서 최댓값이 돼야함
원형이라 왼쪽 오른쪽 연결돼 있음
*/

import java.util.*;

class Solution {
    
    public int findMaxSum(int from, int to, int[] sticker) {
    int[] sumArr = new int[sticker.length - 1];

    sumArr[0] = sticker[from];
    sumArr[1] = Math.max(sticker[from + 1], sticker[from]);
    for (int i = from + 2, sumIndex = 2; i <= to; i++, sumIndex++) {
        sumArr[sumIndex] = Math.max((sticker[i] + sumArr[sumIndex - 2]), sumArr[sumIndex - 1]);
    }

    return sumArr[sumArr.length - 1];

    }

    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        } else if(sticker.length == 2) {
            return Math.max(sticker[0], sticker[1]);
        } else {
            return Math.max(findMaxSum(0, sticker.length - 2, sticker), findMaxSum(1, sticker.length - 1, sticker));
        }
    }
}