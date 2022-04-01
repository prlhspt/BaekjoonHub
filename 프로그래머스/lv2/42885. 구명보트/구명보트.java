import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] people, int limit) {
        int boat = 0;

        List<Integer> peopleList = Arrays.stream(people)
                .boxed()
                .collect(Collectors.toList());

        Collections.sort(peopleList);
        int left = 0;
        int right = peopleList.size() - 1;

        while (peopleList.size() > 0) {
            int p = peopleList.get(left);
            left++;
            
            if (p > (limit / 2)) {
                boat += peopleList.size();
                break;
            }

            // [50, 50, 70, 80] 이면 50 꺼내고 80부터 돌면 될 듯?
            // p는 50이고, i는 80, 70, 50 ...
             for (int i = right; i >= 0; i--) {
                 if (peopleList.get(i) <= (limit - p)) {
                     right--;
                     break;
                 }
             }
            boat++;
        }

        return boat;
    }
}