import java.util.*;

class Solution {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    Map<Character, Integer> d = Map.of('U', 0, 'L', 1, 'D', 2, 'R', 3);

    public int solution(String dirs) {

        HashSet<List<Integer>> visited = new HashSet<>();

        int answer = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < dirs.length(); i++) {
            int dir = d.get(dirs.charAt(i));
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5)
                continue;

            if (!visited.contains(List.of(x, y, nx, ny))) {
                visited.add(List.of(x, y, nx, ny));
                visited.add(List.of(nx, ny, x, y));
                answer++;
            }
            x = nx;
            y = ny;
        }
        return answer;
    }
}



