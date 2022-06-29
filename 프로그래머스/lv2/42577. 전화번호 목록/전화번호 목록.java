import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();

        Arrays.sort(phone_book);
        
        for (String s : phone_book) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                sb.append(c);
                if (set.contains(sb.toString())) {
                    return false;
                }
            }
            set.add(sb.toString());
        }
        return true;
    }
}