import java.util.*;

class Solution {
    
    int ANSWER;
    
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void perm(int k, int cur, int[] nums, int[] pick) {
        if (k == 3) {
            int val = 0;
            for (int p : pick) {
                val += p;
            }
            if (isPrime(val)) {
                ANSWER++;
            }
            return;
        }
        
        for (int i = cur; i < nums.length; i++) {
            pick[k] = nums[i];
            perm(k+1, i+1, nums, pick);
            pick[k] = 0;
        }
    }
    
    public int solution(int[] nums) {
        
        int[] pick = new int[3];
        
        perm(0, 0, nums, pick);

        return ANSWER;
    }
}