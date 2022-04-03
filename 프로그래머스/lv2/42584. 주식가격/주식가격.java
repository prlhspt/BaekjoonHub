/**
가격이 떨어지지 않은 기간은 몇 초 인가

[1, 2, 3, 2, 3]

i 랑 i+1로 비교하면 안됨?

*/

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        int sum = 0;
        
        for (int i = 0; i < prices.length; i++) {
            sum = 0;
            for (int j = i+1; j < prices.length; j++) {
                sum++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}