/** 
인접한 집은 못훔침
집이 원형이라 0번 인덱스랑 마지막 인덱스도 비교해줘야 함
1, 2, 3, 1

0, 1번은 그냥 패스
2번은 max(1번까지 털 수 있는 돈, 0번까지 털 수 있는 돈 + 2번의 돈)
이렇게 하면 집이 원형으로 안 연결되어있다는 가정 하에 최댓값

[10, 2, 5, 30]
10이랑 30이 안붙어있으면 40
10이랑 30이 붙어있으면 32

[10, 2, 5, 30, 40]
10이랑 40이 안붙어있으면 55
10이랑 40이 붙어있으면 45

[2, 5, 30, 40]
이면 
2부터 시작해서 45

[10, 2, 3, 30]
32

[40, 2, 3, 30]

*/

import java.util.*;

class Solution {
    
    void reverseArray(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
    }
    
    public int solution(int[] money) {

        int[] moneyClone = money.clone();

        // 앞에서부터 뒤로 가기
        money[1] = Math.max(money[0], money[1]);

        for (int i = 2; i < money.length-1; i++) {
            money[i] = Math.max(money[i-2] + money[i], money[i-1]);
        }

        // 뒤에서부터 앞으로 가기
        reverseArray(moneyClone);
        moneyClone[1] = Math.max(moneyClone[0], moneyClone[1]);

        for (int i = 2; i < money.length-1; i++) {
            moneyClone[i] = Math.max(moneyClone[i-2] + moneyClone[i], moneyClone[i-1]);
        }

        return Math.max(moneyClone[money.length - 2], money[money.length - 2]);
     }
}