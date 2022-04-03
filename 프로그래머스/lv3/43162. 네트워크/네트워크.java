import java.util.*;

/*
1부터 시작  
*/
class Solution {

    static int[] parent;
    
    int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }
    
    void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
    
    public int solution(int n, int[][] computers) {
        
        // [[1, 1, 0], [1, 1, 1], [0, 1, 1]]
        // 1 -> 2, 2 -> 3
        
        int result = 0;
        
        List<List<Integer>> list = new ArrayList<>();
        Set<Integer> resultSet = new HashSet<>();
        
        for (int i = 0; i < computers.length; i++) {
            for (int j = i+1; j < computers[i].length; j++) {
                if (computers[i][j] == 1) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i+1);
                    temp.add(j+1);
                    list.add(temp);    
                }
            }
        }
        
        int e = list.size();
        parent = new int[n+1];
            
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < e; i++) {
            unionParent(list.get(i).get(0), list.get(i).get(1));
        }
        
        for (int i = 1; i <= n; i++) {
            int p = findParent(i);
            if (!resultSet.contains(p)) {
                resultSet.add(p);
                result++;
            }
        }

        return result;
    }
}