import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();

        int[] dates = new int[progresses.length];

        for (int i = 0; i < progresses.length; i++) {
            if ((100 - progresses[i]) % speeds[i] == 0) {
                dates[i] = (100 - progresses[i]) / speeds[i];
            } else {
                dates[i] = (100 - progresses[i]) / speeds[i] + 1;
            }
        }
        
        int count = 0;
        int index = 0;
        int day = 1;

        while (count < dates.length) {
            index++;

            if ((count + day) == dates.length) {
                result.add(day);
                return result.stream().mapToInt(i -> i).toArray();
            }

            if (dates[count] < dates[count + day]) {
                result.add(day);
                count = index;
                day = 1;
            } else {
                day++;
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

}