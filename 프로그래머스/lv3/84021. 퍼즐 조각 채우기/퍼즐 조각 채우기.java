import java.util.*;

/*
1. game_board 빈 곳 bfs로 좌표화
2. table 차있는 곳 bfs로 좌표화
3. gameboard 기준으로 모든 table 시계방향으로 4번 돌려보면서 칸수 초과 안하고 제일 많이 채운 것 고르기
 */

import java.util.*;

class Solution {

    int length;
    int[] x = {0, -1, 0, 1}, y = {-1, 0, 1, 0};

    class Block  {
        private int x;
        private int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

    void bfs(int[][] board, boolean[][] visited, List<int[][]> blockArrayList, int findNum, int a, int b) {

        Queue<Block> queue = new ArrayDeque<>();

        List<Block> blockList = new ArrayList<>();

        queue.offer(new Block(a, b));

        while (!queue.isEmpty()) {

            Block now = queue.poll();
            blockList.add(now);

            for (int i = 0; i < 4; i++) {

                // 인덱스 초과
                if (now.getX()+x[i] < 0 || now.getX()+x[i] > length - 1
                        || now.getY()+y[i] < 0 || now.getY()+y[i] > length - 1) {
                    continue;
                }

                if (!visited[now.getX()+x[i]][now.getY()+y[i]] && board[now.getX()+x[i]][now.getY()+y[i]] == findNum) {
                    visited[now.getX()+x[i]][now.getY()+y[i]] = true;
                    queue.offer(new Block(now.getX()+x[i], now.getY()+y[i]));
                }
            }
        }

        Collections.sort(blockList, (o1, o2) -> Integer.compare(o1.getX(), o2.getX()));
        int firstX = blockList.get(0).getX();
        int xSize = blockList.get(blockList.size() - 1).getX() - blockList.get(0).getX();

        Collections.sort(blockList, (o1, o2) -> Integer.compare(o1.getY(), o2.getY()));
        int firstY = blockList.get(0).getY();
        int ySize = blockList.get(blockList.size() - 1).getY() - blockList.get(0).getY();

        int[][] blockArray = new int[xSize+1][ySize+1];

        for (int i = 0; i < blockList.size(); i++) {
            blockArray[blockList.get(i).getX() - firstX][blockList.get(i).getY() - firstY] = 1;
        }

        blockArrayList.add(blockArray);
    }

    List<int[][]> findBlock(int[][] board, int findNum) {

        List<int[][]> blockArrayList = new ArrayList<>();

        boolean[][] visited = new boolean[length][length];

        for (int a = 0; a < length; a++) {
            for (int b = 0; b < length; b++) {
                if (!visited[a][b] && board[a][b] == findNum) {
                    visited[a][b] = true;
                    bfs(board, visited, blockArrayList, findNum, a, b);
                }
            }

        }
        return blockArrayList;
    }

    int[][] rotate(int[][] array) {

        int m = array.length;
        int n = array[0].length;
        int[][] rotateArray = new int[n][m];

        for (int a = 0; a < m; a++) {
            for (int b = 0; b < n; b++) {
                rotateArray[b][m - 1 - a] = array[a][b];
            }
        }

        return rotateArray;

    }

    public boolean compareBoard(int[][] game_board, int[][] table) {
        int gM = game_board.length;
        int gN = game_board[0].length;
        int tM = table.length;
        int tN = table[0].length;

        if (gM != tM || gN != tN) {
            return false;
        }

        for (int a = 0; a < gM; a++) {
            for (int b = 0; b < gN; b++) {
                if (game_board[a][b] != table[a][b]) {
                    return false;
                }
            }
        }

        return true;

    }

    public int countBoard(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int count = 0;

        for (int a = 0; a < m; a++) {
            for (int b = 0; b < n; b++) {
                if (board[a][b] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int solution(int[][] game_board, int[][] table) {

        int answer = 0;

        length = game_board.length;

        List<int[][]> gameBoardList = findBlock(game_board, 0);
        List<int[][]> tableList = findBlock(table, 1);

        for (int a = 0; a < gameBoardList.size(); a++) {
            int[][] gameBoardArray = gameBoardList.get(a);
            for (int b = 0; b < tableList.size(); b++) {
                int[][] tableArray = tableList.get(b);
                boolean result = false;
                for (int c = 0; c < 4; c++) {
                    tableArray = rotate(tableArray);
                    result = compareBoard(gameBoardArray, tableArray);
                    if (result == true) {
                        break;
                    }
                }

                if (result == true) {
                    gameBoardList.remove(a);
                    a--;
                    tableList.remove(b);
                    answer += countBoard(tableArray);
                    break;
                }
            }
        }

        return answer;
    }

}