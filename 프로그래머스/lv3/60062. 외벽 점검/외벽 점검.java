import java.util.*;

class Solution {
    
    static int INF = 987654321;
    
    int[] temp;
    boolean[] visited;
    
    List<int[]> resultList = new ArrayList<>();
    
    public void permutation(int[] arr, int r, int idx) {
        if (idx == r) {
            resultList.add(Arrays.copyOf(temp, temp.length));
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            
            temp[idx] = arr[i];
            visited[i] = true;
            permutation(arr, r, idx+1);
            visited[i] = false;
            
        }
        
    }
    
    int upperBound(int[] arr, int target) {
        int lo = -1;
        int hi = arr.length + 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) lo = mid;
            else hi = mid;
        }
        return hi;
    }
    
    
    public int solution(int n, int[] weak, int[] dist) {
        
        int answer = INF;
        int W = weak.length;
        
        visited = new boolean[dist.length];
        temp = new int[dist.length];
        
        weak = Arrays.copyOf(weak, W*2);
        for (int i = W; i < weak.length; i++)
            weak[i] = weak[i-W] + n;
        
        permutation(dist, dist.length, 0);
        
        for (int[] result : resultList) {
            for (int i = 0; i < W; i++) {
                int start = weak[i];
                int finish = weak[i+W-1];
                for (int j = 0; j < result.length; j++) {
                    start += result[j];
                    if (start >= finish) {
                        answer = Math.min(answer, j+1);
                        break;
                    }
                    
                    int next = upperBound(weak, start);
                    start = weak[next];
                }
            }
        }
        
        return (answer == INF) ? -1 : answer;
    }
    
}