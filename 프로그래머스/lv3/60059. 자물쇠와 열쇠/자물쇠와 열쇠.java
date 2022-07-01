import java.util.Arrays;

class Solution {

    public int[][] rotate(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                copy[i][j] = arr[arr.length - j - 1][i];
            }
        }
        return copy;
    }

    public int[][] copyArray(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copy;
    }

    public boolean check(int[][] key, int[][] lock, int n, int start, int end) {

        int limit = n - key.length + 1;

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                int[][] copyLock = copyArray(lock);

                checkLockKeySize(copyLock, key, i, j);

                boolean flag = true;
                for (int k = start; k < end; k++) {
                    for (int l = start; l < end; l++) {
                        if (copyLock[k][l] != 1) {
                            flag = false;
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

    private void checkLockKeySize(int[][] copyLock, int[][] key, int i, int j) {
        for (int a = 0; a < key.length; a++) {
            for (int b = 0; b < key.length; b++) {
                if (copyLock[i+a][j+b] == 1 && key[a][b] == 1) {
                    return;
                }

                if (copyLock[i+a][j+b] == 0 && key[a][b] == 1) {
                    copyLock[i+a][j+b] = 1;
                }
            }
        }
    }

    public boolean solution(int[][] key, int[][] lock) {

        int n = lock.length + (key.length - 1) * 2;

        int start = key.length - 1;
        int end = key.length - 1 + lock.length;

        int[][] extendLock = new int[n][n];

        int lockCount = 0;

        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                extendLock[i][j] = lock[i - start][j - start];
                if (lock[i - start][j - start] == 0) {
                    lockCount++;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            key = rotate(key);
            if (check(key, extendLock, n, start, end)) {
                return true;
            }
        }

        return false;
    }
}