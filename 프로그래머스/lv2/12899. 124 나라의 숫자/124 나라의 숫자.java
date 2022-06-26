class Solution {

    public StringBuilder solve(long n, StringBuilder sb) {
        if (n < 3) {
            if (n != 0) {
                sb.insert(0, n);
            }
            return sb;
        }
        if (n % 3 == 0) {
            sb.insert(0, n % 3);
            sb = solve((n - 3) / 3, sb);

        } else {
            sb.insert(0, n % 3);
            sb = solve(n / 3, sb);
        }

        return sb;
    }

    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        sb = solve(n, sb);
        String answer = sb.toString().replace("0", "4");
        answer = answer.replace("3", "4");

        System.out.println("answer = " + answer);

        return answer;
    }
}