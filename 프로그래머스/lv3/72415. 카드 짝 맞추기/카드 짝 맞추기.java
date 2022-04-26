import java.util.*;

class Solution {
    
    class Point {
        int row, col, cnt;
        
        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
    
    static final int INF = 987654231;
    static final int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    int[][] Board;
    
    public int bfs(Point src, Point dst) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        
        q.offer(src);
        
        while (!q.isEmpty()) {
            Point curr = q.poll();
            
            if (curr.row == dst.row && curr.col == dst.col)
                return curr.cnt;
                        
            visited[curr.row][curr.col] = true;
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.row + D[i][0], nc = curr.col + D[i][1];
                if (nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc, curr.cnt + 1));
                }
                
                for (int j = 0; j < 2; j++) {

                    if (Board[nr][nc] != 0) break;
                    if (nr + D[i][0] < 0 || nr + D[i][0] > 3 || nc + D[i][1] < 0 || nc + D[i][1] > 3) break;
                    
                    nr += D[i][0];
                    nc += D[i][1];
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc, curr.cnt + 1));
                }
            }
        }
        
        return INF;
    }
    
    public int permutate(Point src) {
        int ret = INF;
        
        for (int num = 1; num <= 6; num++) {
            List<Point> card = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Board[i][j] == num) {
                        card.add(new Point(i, j, 0));
                    }
                }
            }
            
            if (card.isEmpty()) continue;
            
            int one = bfs(src, card.get(0)) + bfs(card.get(0), card.get(1)) + 2;
            int two = bfs(src, card.get(1)) + bfs(card.get(1), card.get(0)) + 2;
            
            for (int i = 0; i < 2; i++) {
                Board[card.get(i).row][card.get(i).col] = 0;
            }
            
            ret = Math.min(ret, one + permutate(card.get(1)));
            ret = Math.min(ret, two + permutate(card.get(0)));
            
            for (int i = 0; i < 2; i++) {
                Board[card.get(i).row][card.get(i).col] = num;
            }
            
            
        }
        
        if (ret == INF) return 0;          
        
        return ret;
        
    }

    /*
    1. 카드 찾기
    2. 커서부터 1번카드까지 거리 계산
    3. 역순
    4. 백트래킹으로 1번에서 2번, 2번에서 3번, ~~~
    5. 최솟값 갱신
    */
    
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r, c, 0));
    }
}