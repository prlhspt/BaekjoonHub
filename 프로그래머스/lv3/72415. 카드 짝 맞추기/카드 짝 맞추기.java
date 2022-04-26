import java.util.*;

class Solution {
    
    class Point {
        int row, col, cnt;
        
        Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
    
    static final int INF = 987654321;
    static final int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    int[][] Board;
    
    int bfs(Point src, Point dst) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.row == dst.row && curr.col == dst.col)
                return curr.cnt;
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.row + D[i][0], nc = curr.col + D[i][1];
                if (nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.cnt + 1));
                }
                
                // 앞으로 한 칸 간 다음에 경계까지 최대 두 칸 더 갈 수 있으므로 j < 2로 했음, 어짜피 if문으로 break 조건 걸어주기 때문에 2보다 더 커도 결과는 지장 없음
                for (int j = 0; j < 2; j++) {
                    // 카드 만났을때
                    if (Board[nr][nc] != 0) break;
                    // 경계면까지 갔을때
                    if (nr + D[i][0] < 0 || nr + D[i][0] > 3 
                        || nc + D[i][1] < 0 || nc + D[i][1] > 3)
                        break;
                    nr += D[i][0];
                    nc += D[i][1];
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.cnt + 1));
                }
                
            }
            
        }
        
        return INF;
    }
    
    int permutate(Point src) {
        int ret = INF;
        
        // 카드 6개 중 순차적으로 찾기, 예를 들어 num이 1이면 1번 카드 2개를 찾음
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
            
            // 커서 -> 첫번째 카드 -> 두번째 카드 길이
            int one = bfs(src, card.get(0)) + bfs(card.get(0), card.get(1)) + 2;
            // 커서 -> 두번째 카드 -> 첫번째 카드 길이
            int two = bfs(src, card.get(1)) + bfs(card.get(1), card.get(0)) + 2;
            
            // 첫번째 카드에 대한 거리 처리 후에 두번째 카드 차례 할 때는 카드 지워줘야 함
            for (int i = 0; i < 2; i++)
                Board[card.get(i).row][card.get(i).col] = 0;

            ret = Math.min(ret, one + permutate(card.get(1)));
            ret = Math.min(ret, two + permutate(card.get(0)));
           
            // 1번에 대한 재귀 후 2번 차례 할 때는(num 1->2) 카드가 다시 원상복귀 되어야 함
            for (int i = 0; i < 2; i++)
                Board[card.get(i).row][card.get(i).col] = num;
            
        }
        
        if (ret == INF) return 0;
        
        return ret;
    }
    
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r, c, 0));
    }
}