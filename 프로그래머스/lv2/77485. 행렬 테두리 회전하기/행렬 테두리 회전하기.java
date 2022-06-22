class Solution {

    public int[][] MAP;

    public int move(int fR, int fC, int lR, int lC) {

        int answer = MAP[fR][fC];

        int temp = MAP[fR][fC];

        for (int i = fR; i < lR; i++) {
            MAP[i][fC] = MAP[i+1][fC];
            answer = Math.min(answer, MAP[i][fC]);
        }

        for (int i = fC; i < lC; i++) {
            MAP[lR][i] = MAP[lR][i+1];
            answer = Math.min(answer, MAP[lR][i]);
        }

        for (int i = lR; i > fR; i--) {
            MAP[i][lC] = MAP[i-1][lC];
            answer = Math.min(answer, MAP[i][lC]);
        }

        for (int i = lC; i > fC; i--) {
            MAP[fR][i] = MAP[fR][i-1];
            answer = Math.min(answer, MAP[fR][i]);
        }

        MAP[fR][fC+1] = temp;

        return answer;
    }

    public int[] solution(int rows, int columns, int[][] queries) {

        MAP = new int[rows][columns];
        int[] answer = new int[queries.length];

        for (int i = 0; i < rows*columns; i++) {
            int r = i / columns;
            int c = i % columns;
            MAP[r][c] = i+1;
        }

        for (int i = 0; i < queries.length; i++) {
             answer[i] = move(queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1);
        }

        return answer;
    }
}