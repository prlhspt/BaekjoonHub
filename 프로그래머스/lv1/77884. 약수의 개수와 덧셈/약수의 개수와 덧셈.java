class Solution {

    public boolean findNumber(int num) {
        if ((int) Math.sqrt(num) == Math.sqrt(num)) {
            return false;
        }
        return true;
    }

    public int solution(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            boolean number = findNumber(i);
            if (number) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        return answer;
    }
}