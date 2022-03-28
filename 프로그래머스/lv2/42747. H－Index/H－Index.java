/**
h-index
발표한 논문 n 편,
h번 인용된 논문 h 편 이상,
나머지 논문이 h 편 이하

citations = [3, 0, 6, 1, 5]
citations = [6, 5, 3, 1, 0]

for loop
citations[i] >  i+1

*/

import java.util.*;

class Solution {
    public int solution(int[] citations) {

        int[] ints = Arrays.stream(citations).boxed()
                .sorted(Collections.reverseOrder()).mapToInt(i -> i).toArray();

        for (int i = 0; i < ints.length; i++) {
            if (ints[i] < i + 1) {
                return i;
            } 
        }
        return citations.length;
    }
}