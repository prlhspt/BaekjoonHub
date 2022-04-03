import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] people, int limit) {
        int boat = 0;

        Arrays.sort(people);

        ArrayDeque<Integer> peopleDeq = Arrays.stream(people)
                .boxed()
                .collect(Collectors.toCollection(() -> new ArrayDeque<>()));


        // {30, 50, 50, 70, 80};
        // 왼쪽 + 오른쪽 해서 limit 아래면 두명 보트에 태우면 됨
        while (peopleDeq.size() > 1) {
            int firstP = peopleDeq.peekFirst();
            int lastP = 0;
            while (peopleDeq.size() > 0) {
                lastP = peopleDeq.pollLast();
                boat++;
                // 30 + 80 <= 100 X 80만 타고 보내기
                // 30 + 70 <= 100 O
                if (firstP + lastP <= limit) {
                    peopleDeq.pollFirst();
                    break;
                }
            }
        }

        if (peopleDeq.size() == 1) {
            boat++;
        }

        return boat;
    }
}