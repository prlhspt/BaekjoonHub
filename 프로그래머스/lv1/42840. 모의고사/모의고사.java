import java.util.*;

/**
1번 : 1, 2, 3, 4, 5 ... 5개씩
2번 : 2, 1, 2, 3, 2, 4, 2, 5 ... 8개씩
3번 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 ... 10개씩

*/

class Solution {
   public int[] solution(int[] answers) {
       
       int[] giveup1 = new int[10000];
       int[] giveup2 = new int[10000];
       int[] giveup3 = new int[10000];
       
       int giveup1Answer = 0;
       int giveup2Answer = 0;
       int giveup3Answer = 0;
       
       int index = 0;
       while (index < 10000) {
           giveup1[index++]  = 1;
           giveup1[index++]  = 2;
           giveup1[index++]  = 3;
           giveup1[index++]  = 4;
           giveup1[index++]  = 5;
       }
       
       index = 0;
       while (index < 10000) {
           giveup2[index++]  = 2;
           giveup2[index++]  = 1;
           giveup2[index++]  = 2;
           giveup2[index++]  = 3;
           giveup2[index++]  = 2;
           giveup2[index++]  = 4;
           giveup2[index++]  = 2;
           giveup2[index++]  = 5;
       }
       
       index = 0;
       while (index < 10000) {
           giveup3[index++]  = 3;
           giveup3[index++]  = 3;
           giveup3[index++]  = 1;
           giveup3[index++]  = 1;
           giveup3[index++]  = 2;
           giveup3[index++]  = 2;
           giveup3[index++]  = 4;
           giveup3[index++]  = 4;
           giveup3[index++]  = 5;
           giveup3[index++]  = 5;
       }
       
       for (int i = 0; i < answers.length; i++) {
           if (giveup1[i] == answers[i]) {
               giveup1Answer++;               
           }
           
           if (giveup2[i] == answers[i]) {
               giveup2Answer++;
           }
           
           if (giveup3[i] == answers[i]) {
               giveup3Answer++;               
           }
       }
       
       // 1 == 2 == 3
       if (giveup1Answer == giveup2Answer && giveup1Answer == giveup3Answer) {
           int[] answer = new int[3];
           answer[0] = 1;
           answer[1] = 2;
           answer[2] = 3;
           return answer;
       }
       
       // 1, 2 > 3
       if (giveup1Answer == giveup2Answer && giveup1Answer > giveup3Answer) {
           int[] answer = new int[2];
           answer[0] = 1;
           answer[1] = 2;
           return answer;
       }
       // 1, 3 > 2
       if (giveup1Answer == giveup3Answer && giveup1Answer > giveup2Answer) {
           int[] answer = new int[2];
           answer[0] = 1;
           answer[1] = 3;
           return answer;
       }
       // 2, 3 > 1
       if (giveup2Answer == giveup3Answer && giveup2Answer > giveup1Answer) {
           int[] answer = new int[2];
           answer[0] = 2;
           answer[1] = 3;
           return answer;
       }
       // 1 > 2, 3
       if (giveup1Answer > giveup2Answer && giveup1Answer > giveup3Answer) {
           int[] answer = new int[1];
           answer[0] = 1;
           return answer;
       }
       // 2 > 1, 3
       if (giveup2Answer > giveup1Answer && giveup2Answer > giveup3Answer) {
           int[] answer = new int[1];
           answer[0] = 2;
           return answer;
       }
       // 3 > 1, 2
       if (giveup3Answer > giveup1Answer && giveup3Answer > giveup2Answer) {
           int[] answer = new int[1];
           answer[0] = 3;
           return answer;
       }
       
       return new int[1];
   }
}