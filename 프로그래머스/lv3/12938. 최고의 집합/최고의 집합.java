/*
n <= 10000
s <= 100,000,000

n = 2, s = 9
{1, 8}, {2, 7}, {3, 6}, {4, 5}

n = 3, s = 100,000,000

100,000,000 / 3

100,000,000 / 3

100,000,000 / 3

+ 100,000,000 % 3

4, 10

2, 2, 3, 3

4, 5


*/
class Solution {
    public int[] solution(int n, int s) {
        
        if (n > s) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (s / n);
        }
        
        for (int i = 0; i < (s%n); i++) {
            answer[n-i-1]++;
        }
        
        return answer;
    }
}