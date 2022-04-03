/**
7일 때
3까지 갈 수 있는 최댓값은 11
8은 15

3일 때
8까지 최댓값은 19
1은 12

8일 때
1까지 최댓값은 16
0까지 최댓값은 15

1번, 마지막 인덱스는 마지막것만
가운데 것은 (0, 1), (1, 2), (1, 3) 이런식으로

*/

import java.util.*;

class Solution {
    public int solution(int[][] triangle) {

        int[][] result = Arrays.stream(triangle)
                .map(ints -> ints.clone())
                .toArray(value -> new int[value][]);

        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], 0);
        }

        result[0][0] = triangle[0][0];

        // 7을 기준으로 max(7 + 8번에 있는 최댓값, 7 + 1번에 있는 최댓값)
        // 앞에 것을 기준으로 뒤에꺼 탐색할꺼고, 1번부터 시작함
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {

                if (j == 0) {
                    result[i][j] = triangle[i][j] + result[i-1][j];
                } else if (j == triangle[i].length - 1) {
                    result[i][j] = triangle[i][j] + result[i-1][j - 1];
                } else {
                    result[i][j] = Math.max(triangle[i][j] + result[i-1][j-1],
                            triangle[i][j] + result[i-1][j]);
                }
            }
        }

        return Arrays.stream(result[result.length - 1]).max().getAsInt();
    }
}