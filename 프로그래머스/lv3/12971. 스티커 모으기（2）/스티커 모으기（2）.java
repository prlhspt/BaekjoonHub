/*
다이나믹 프로그래밍
[14, 6, 5, 11, 3, 9, 2, 10]
이웃한건 못뺌
연속 안되게 선택해서 최댓값이 돼야함
원형이라 왼쪽 오른쪽 연결돼 있음
*/

import java.util.*;

class Solution {

    int[] reverseArray(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
        return arr;
    }

    public int solution(int sticker[]) {

        if (sticker.length == 1) {
            return sticker[0];
        }

        int answer = 0;

        int[] ansArr = new int[sticker.length];        

        // i[0]까지의 최댓값 + i[2]하고, i[1]까지의 최댓값 중 더 큰거 고르면 됨

        ansArr[0] = sticker[0];
        ansArr[1] = Math.max(ansArr[0], sticker[1]);
        for (int i = 2; i < sticker.length - 1; i++) {
            ansArr[i] = Math.max(ansArr[i-2] + sticker[i], ansArr[i-1]);
        }

        int[] revArr = new int[sticker.length];
        reverseArray(sticker);

        revArr[0] = sticker[0];
        revArr[1] = Math.max(revArr[0], sticker[1]);

        for (int i = 2; i < sticker.length - 1; i++) {
            revArr[i] = Math.max(revArr[i-2] + sticker[i], revArr[i-1]);
        }

        answer = Math.max(ansArr[ansArr.length-2], revArr[revArr.length-2]);

        return answer;
    }
}