/**
진법 변환 한다음에 하나씩 돌면서 조건 걸러내면 될 듯

*/

class Solution {
    
    public boolean isPrime(long num) {
        
        if (num < 2) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int n, int k) {

        int answer = 0;
        
        String s = Integer.toString(n, k);
        
        // 211 0 2 0 1 0 1 0 11
        // 0 만나면 그 전까지 수들 처리해주는 아이디어도 좋은듯
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (isPrime(Long.parseLong(s.substring(start, i)))) {
                    answer++;
                }
                start = i;
            }   
        }
        
        if (isPrime(Long.parseLong(s.substring(start, s.length())))) {
            answer++;
        }    
        
        return answer;
    }
}