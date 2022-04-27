import java.util.*;

/*
1. 부모 노드에서 자식 노드로 DFS 타고 들어간다.
2. 끝까지 도착하면 끝의 값을 그 바로 전 값에 합친다.
3. 합친 값만큼 answer에 더한다.
*/

class Solution {

    class Node {
        List<Integer> child = new ArrayList<>();
    }

    List<Node> list;
    long answer;
    long[] sum;

    public void dfs(int now, int parent) {

        for(int i = 0; i < list.get(now).child.size(); i++) {
            if (list.get(now).child.get(i) != parent)
                dfs(list.get(now).child.get(i), now);
        }

        sum[parent] += sum[now];
        answer += Math.abs(sum[now]);
    }

    public long solution(int[] a, int[][] edges) {

        sum = new long[a.length];

        for (int i = 0; i < a.length; i++)
            sum[i] = a[i];

        list = new ArrayList<>();

        for (int i = 0; i < a.length; i++)
            list.add(new Node());

        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).child.add(edges[i][1]);
            list.get(edges[i][1]).child.add(edges[i][0]);
        }

        dfs(0, 0);
        
        if (sum[0] == 0) {
            return answer;
        } else {
            return -1;
        }
    }
}