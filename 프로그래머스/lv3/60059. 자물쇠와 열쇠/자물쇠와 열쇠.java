import java.util.Arrays;

class Solution {

    public int[][] rotate(int[][] arr) {
        int[][] copy = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }

    public boolean check(int[][] key, int[][] lock) {
        // 키 1이랑 lock 0이 같아야 함
        // key 가 더 크니까 key 에다가 lock 갖다대기

        int n = key.length - lock.length;
        int m = key[0].length - lock[0].length;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                boolean flag = true;
                for (int a = 0; a < lock.length; a++) {
                    for (int b = 0; b < lock[0].length; b++) {
                        if (lock[a][b] != key[a + i][b + j]) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] makeOptimizeLock(int[][] lock) {
        int minX = 987654321;
        int minY = 987654321;
        int maxX = 0;
        int maxY = 0;

        // 락의 0이랑 키의 1이랑 딱 맞아야 함
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, i);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, i);
                }
            }
        }

        int[][] optimizeLock = new int[maxX - minX + 1][maxY - minY + 1];
        for (int i = minX; i <= maxX;  i++) {
            for (int j = minY; j <= maxY ; j++) {
                optimizeLock[i - minX][j - minY] = lock[i][j];
            }
        }

        return optimizeLock;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int[][] optimizeLock = makeOptimizeLock(lock);

        for (int i = 0; i < 4; i++) {
            optimizeLock = rotate(optimizeLock);
            if (key.length > optimizeLock.length) {
                if (check(key, optimizeLock)) {
                    return true;
                }
            } else {
                if (check(optimizeLock, key)) {
                    return true;
                }
            }
            
        }



        return false;
    }
}