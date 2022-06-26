class Solution {

    public long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public long solution(int w, int h) {
        long lw = w;
        long lh = h;
        long gcd;
        if (lw >= lh) {
            gcd = gcd(lw, lh);
        } else {
            gcd = gcd(lh, lw);
        }
        long a = lw / gcd;
        long b = lh / gcd;
        long answer;

        if (lw == lh) {
            answer = lw * lh - lw;
        } else {
            answer = lw * lh - (a + b - 1) * gcd;
        }

        return answer;
    }
}