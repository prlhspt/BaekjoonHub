import java.util.*;
import java.util.regex.*;

class Solution {
    public boolean solution(String[] phone_book) {

        Map<String, Integer> map = new HashMap<>();

        for (String p : phone_book) {
            map.put(p, 1);
        }

        String temp;

        for (String p : phone_book) {
            temp = "";
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);

                temp = temp + c;

                if (map.getOrDefault(temp, -1) > 0 && !temp.equals(p)) {
                    return false;
                }
            }
        }

        return true;
    }
}