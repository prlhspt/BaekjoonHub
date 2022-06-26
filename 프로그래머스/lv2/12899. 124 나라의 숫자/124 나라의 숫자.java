class Solution {

    public String[] arr = {"1", "2", "4"};

    public StringBuilder solve(int n, StringBuilder sb) {
        sb.insert(0, arr[n % 3]);
        if (n / 3 == 0) {
            return sb;
        }
        return solve(n / 3 - 1, sb);
    }

    public String solution(int n) {
        n--;

        StringBuilder sb = new StringBuilder();
        sb = solve(n, sb);

        return sb.toString();
    }
}