class Solution {
    public int solution(int n) {
        StringBuilder sb = new StringBuilder();

        String string = Integer.toString(n, 3);
        for (char c : string.toCharArray()) {
            sb.insert(0, c);
        }
        return Integer.parseInt(sb.toString(), 3);
    }
}