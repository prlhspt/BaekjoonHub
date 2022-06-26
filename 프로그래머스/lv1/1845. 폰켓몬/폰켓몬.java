import java.util.HashSet;
import java.util.Set;

class Solution {

    public int solution(int[] nums) {
        
        int answer;
        
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums) {
            set.add(n);
        }
        
        if (set.size() > nums.length / 2) {
            answer = nums.length / 2;
        } else {
            answer = set.size();
        }

        return answer;
    }

}