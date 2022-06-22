import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {

        int zeroCnt = 0;
        int matchCnt = 0;

        int[] arr = new int[50];

        for (int i = 0; i < win_nums.length; i++) {
            arr[win_nums[i]]++;
        }

        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zeroCnt++;
            } else if (arr[lottos[i]] > 0) {
                arr[lottos[i]]--;
                matchCnt++;
            }
        }

        int[] answer = new int[2];



        if (zeroCnt + matchCnt >= 2) {
            answer[0] = 7 - (zeroCnt + matchCnt);
        } else {
            answer[0] = 6;
        }

        if (matchCnt >= 2) {
            answer[1] = 7 - matchCnt;
        } else {
            answer[1] = 6;
        }

        return answer;
    }
}