import java.util.Map;

class Solution {

    Map<Character, Integer> MAP = Map.of('(', -1, ')', 1);

    public int find(StringBuilder sb) {
        char[] chars = sb.toString().toCharArray();
        int count = MAP.get(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            count += MAP.get(chars[i]);
            if (count == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public StringBuilder func(StringBuilder p) {
        if (p.length() == 0) {
            return p;
        }

        int i = find(p);
        StringBuilder u = new StringBuilder(p.substring(0, i));
        StringBuilder v = new StringBuilder(p.substring(i, p.length()));

        // 올바른 괄호
        if (p.charAt(0) == '(') {
            return u.append(func(v));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(func(v));
            sb.append(')');
            u.deleteCharAt(0);
            u.deleteCharAt(u.length() - 1);
            String x = u.toString().replace("(", "x");
            x = x.replace(")", "(");
            x = x.replace("x", ")");
            sb.append(x);
            return sb;
        }
    }

    public String solution(String p) {
        StringBuilder sb = new StringBuilder(p);
        return func(sb).toString();
    }

    public static void main(String[] args) {
        String solution = new Solution().solution("()))((()");
        System.out.println("solution = " + solution);
    }
}