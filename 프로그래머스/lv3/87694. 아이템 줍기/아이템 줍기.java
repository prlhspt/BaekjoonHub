import java.util.*;

class Solution {

    int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    int[] dy = {0, -1, 0, 1, -1, 1, 1, -1};

    int minVal = Integer.MAX_VALUE;

    boolean innerBoundaryCheck(int row, int column, int rowLength, int columnLength) {
        if (row >= 0 && row < rowLength && column >= 0 && column < columnLength) {
            return true;
        }
        return false;

    }

    boolean isFull(int[][] graph, int row, int column) {
        // 8방향 다 갔는데 0이 있으면 속이 빈게 아님
        for (int i = 0; i < 8; i++) {
            int x = row + dx[i];
            int y = column + dy[i];

            if (!innerBoundaryCheck(x, y, graph.length, graph[0].length)) {
                return false;
            }

            if (graph[x][y] == 0) {
                return false;
            }
        }

        return true;
    }

    void findRoad(int[][] removeInner, int row, int column, boolean[][] visited, int count) {
        if (removeInner[row][column] == 3) {
            minVal = Math.min(minVal, count);
            return;
        }

        visited[row][column] = true;

        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = column + dy[i];

            if (!visited[x][y] && innerBoundaryCheck(x, y, removeInner.length, removeInner[0].length)
            && removeInner[x][y] != 0) {
                findRoad(removeInner, x, y, visited, ++count);

            }
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        int maxColumn = Arrays.stream(rectangle).min((o1, o2) -> (Integer.compare(o2[2], o1[2])))
                .get()[2];
        int maxRow = Arrays.stream(rectangle).min((o1, o2) -> (Integer.compare(o2[3], o1[3])))
                .get()[3];

        int[][] graph = new int[(maxRow + 1) * 2 + 1][(maxColumn + 1) * 2 + 1];
        int[][] removeInner = new int[(maxRow + 1) * 2 + 1][(maxColumn + 1) * 2 + 1];

        for (int[] rec : rectangle) {

            rec[0] = rec[0] * 2;
            rec[1] = rec[1] * 2;
            rec[2] = rec[2] * 2;
            rec[3] = rec[3] * 2;

            // row
            for (int i = rec[1]; i <= rec[3]; i++) {
                // column
                for (int j = rec[0]; j <= rec[2]; j++) {
                    graph[i][j] = 1;
                    removeInner[i][j] = 1;
                }
            }

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (isFull(graph, i, j)) {
                        removeInner[i][j] = 0;
                    }
                }
            }

            removeInner[characterY][characterX]  = 2;
            removeInner[itemY][itemX] = 3;

        }

        boolean[][] visited = new boolean[(maxRow + 1) * 2 + 1][(maxColumn + 1) * 2 + 1];
        findRoad(removeInner, characterY, characterX, visited, 0);

        return minVal / 2;
    }
}