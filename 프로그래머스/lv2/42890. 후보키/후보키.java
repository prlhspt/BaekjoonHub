import java.util.*;

class Solution {

    List<List<Integer>> LIST = new ArrayList<>();

    boolean[] VISITED;
    String[][] RELATION;


    public void compare() {

        boolean equalFlag = false;

        List<List<String>> compare = new ArrayList<>();
        for (int i = 0; i < RELATION.length; i++) {
            List<String> strTemp = new ArrayList<>();

            for (int j = 0; j < RELATION[i].length; j++) {
                if (VISITED[j]) {
                    strTemp.add(RELATION[i][j]);
                }
            }

            for (int j = 0; j < compare.size(); j++) {
                if (String.join("", compare.get(j)).equals(String.join("", strTemp))) {
                    return;
                }
            }

            compare.add(strTemp);
        }

        List<Integer> intTemp = new ArrayList<>();

        for (int i = 0; i < RELATION[0].length; i++) {
            if(VISITED[i]) {
                intTemp.add(i);
            }
        }

        // 최소키 식별
        for (int i = 0; i < LIST.size(); i++) {
            if (intTemp.containsAll(LIST.get(i))) {
                return;
            }
        }

        LIST.add(intTemp);
    }

    public void func(int k, int t) {

        if (k == t) {
            compare();
            return;
        }

        for (int i = k; i < RELATION[0].length; i++) {
            if (VISITED[i]) continue;

            VISITED[i] = true;
            func(k+1, t);
            VISITED[i] = false;

        }
    }

    public int solution(String[][] relation) {

        RELATION = relation;
        VISITED = new boolean[relation[0].length];

        for (int i = 0; i < RELATION[0].length; i++) {
            func(0, i+1);
        }

        return LIST.size();
    }
}