import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[][] arr;
    static boolean[] equalFlag;
    static int[] dirArr;

    public static void right(int num) {
        int[] oneArr = arr[num];
        int temp = oneArr[oneArr.length - 1];
        for (int i = oneArr.length - 1; i >= 1; i--) {
            oneArr[i] = oneArr[i-1];
        }
        oneArr[0] = temp;
    }

    public static void left(int num) {
        int[] oneArr = arr[num];
        int temp = oneArr[0];
        for (int i = 0; i < oneArr.length - 1; i++) {
            oneArr[i] = oneArr[i+1];
        }
        oneArr[oneArr.length - 1] = temp;
    }

    static void equalCheck(int num, int prevNum, int idx) {
        if (num < 0 || num >= 4) return;

        if (idx == -1) {
            if (arr[num][2] != arr[prevNum][6]) {
                equalFlag[num] = true;
                equalCheck(num-1, num, idx);
            }
        } else if (idx == 1) {
            if (arr[num][6] != arr[prevNum][2]) {
                equalFlag[num] = true;
                equalCheck(num+1, num, idx);

            }
        }
    }
    private static void dirCheck(int num, int dir, int idx) {
        if (num < 0 || num >= 4) return;

        if (dir == 1) {
            dir = -1;
        } else {
            dir = 1;
        }

        dirArr[num] = dir;
        dirCheck(num+idx, dir, idx);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            num--;

            equalFlag = new boolean[4];
            equalFlag[num] = true;

            if (num >= 1) {
                equalCheck(num-1, num, -1);
            }

            if (num <= 2) {
                equalCheck(num+1, num, 1);
            }

            dirArr = new int[4];
            dirArr[num] = dir;

            dirCheck(num-1, dir, -1);
            dirCheck(num+1, dir, 1);

            for (int j = 0; j < 4; j++) {
                if (equalFlag[j]) {
                    if (dirArr[j] == 1) {
                        right(j);
                    } else {
                        left(j);
                    }
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            if (arr[i][0] == 1) {
                sum += 1 << i;
            }
        }

        System.out.println(sum);
    }

}