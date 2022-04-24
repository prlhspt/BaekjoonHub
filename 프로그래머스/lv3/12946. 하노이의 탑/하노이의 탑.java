import java.util.*;

class Solution {
    
    List<int[]> list = new ArrayList<>();
    
    void hanoi(int n, int start, int to, int via) {
        if (n == 1) {
            move(1, start, to);
        } else {
            hanoi(n-1, start, via, to);
            move(n, start, to);
            hanoi(n-1, via, to, start);
        }
    }
    
    private void move(int n, int start, int to) {
        list.add(new int[]{start, to});
    }
    
    
    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2);
        
        int[][] answer = new int[list.size()][2];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i][0] = list.get(i)[0];
            answer[i][1] = list.get(i)[1];
        }
        
        return answer;
    }
}