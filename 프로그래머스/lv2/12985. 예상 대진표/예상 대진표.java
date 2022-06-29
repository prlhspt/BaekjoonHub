class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        int min = Math.min(a, b);
        int max = Math.max(a, b);
        

        int count = 1;
        while (true) {
            if (max % 2 == 0 && min == max - 1) {
                break;
            }

            if (max % 2 == 1) {
                max = max / 2 + 1;
            } else {
                max = max / 2;
            }

            if (min % 2 == 1) {
                min = min / 2 + 1;
            } else {
                min = min / 2;
            }
            count++;
        }

        return count;
    }
}