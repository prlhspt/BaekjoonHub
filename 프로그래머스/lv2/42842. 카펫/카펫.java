import java.util.*;

/**
b = 10, y = 2
BBBB
BYYB
BBBB
(4, 3)

b = 8, y = 1
BBB
BYB
BBB
(3, 3)

b = 24, y = 24
BBBBBBBB
BYYYYYYB
BYYYYYYB
BYYYYYYB
BYYYYYYB
BBBBBBBB
(8, 6)

y의  약수로 계산하면서 될 때까지
*/

class Solution {
    
    List<Integer> divisor(int num) {
        List<Integer> list = new ArrayList<>();
        /*
        12 3.xxx
        1, 2, 3, 4, 6, 12
        
        16
        1, 2, 4, 8, 16
        */
        
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                list.add(i);
            }
        }
        return list;
        
    }
    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        List<Integer> divList = divisor(yellow);
        
        for (int column : divList) {
            int row = yellow / column;
            
            /*
             column = 1, 2, 3, 4
             row = 24, 12, 8, 6
             brown = (row * 2) + ((column + 2) * 2)
            */
             
            int predictBrown = (row * 2) + ((column + 2) * 2);
            
            if (predictBrown == brown) {
                answer[0] = row + 2;
                answer[1] = column + 2;
                return answer;
            }
            
        }
        
        return answer;
    }
}