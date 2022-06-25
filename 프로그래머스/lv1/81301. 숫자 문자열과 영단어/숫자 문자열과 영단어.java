import java.util.*;

class Solution {
    public int solution(String s) {
        
        Map<String, Integer> map = Map.of("zero", 0, "one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);
        
        
        StringBuilder answer = new StringBuilder();
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {  
            if (Character.isDigit(c)) {
                answer.append(c);
                continue;
            } 
            
            sb.append(c);
            
            if (map.get(sb.toString()) != null) {
                answer.append(map.get(sb.toString()));
                sb = new StringBuilder();
            }
        }
        
        return Integer.parseInt(answer.toString());
    }
}