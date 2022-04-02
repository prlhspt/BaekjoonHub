import java.util.*;

class Edge implements Comparable<Edge> {

    private int distance;
    private int nodeA;
    private int nodeB;

    public Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getDistance() {
        return distance;
    }

    public int getNodeA() {
        return nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    @Override
    public int compareTo(Edge o) {
        return (this.distance < o.distance)
                ? -1 : ((this.distance == o.distance)
                ? 0 : 1);
    }
}

class Solution {

    private int v, e;
    private int[] parent;
    private int result = 0;

    ArrayList<Edge> edges = new ArrayList<>();

    private int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public int solution(int n, int[][] costs) {

        // 노드의 개수
        v = n;
        parent = new int[v+1];
        // 간선의 개수
        e = costs.length;

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int cost = costs[i][2];
            edges.add(new Edge(cost, a, b));
        }

        Collections.sort(edges);

        for (int i = 0; i < e; i++) {
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }
        return result;
    }
}