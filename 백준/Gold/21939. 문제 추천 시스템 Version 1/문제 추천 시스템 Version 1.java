import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static class Problem {
        int index;
        int difficulty;

        public Problem(int index, int difficulty) {
            this.index = index;
            this.difficulty = difficulty;
        }
    }

    public static void main(String[] args) throws IOException {

        TreeSet<Problem> set = new TreeSet<>((o1, o2) -> {
            if (o1.difficulty == o2.difficulty) {
                return Integer.compare(o1.index, o2.index);
            }
            return Integer.compare(o1.difficulty, o2.difficulty);
        });

        Map<Integer, Integer> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l);
            set.add(problem);
            map.put(p, l);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("recommend")) {
                String x = st.nextToken();
                Problem problem;
                if (x.equals("1")) {
                    problem = set.last();
                } else {
                    problem = set.first();
                }
                System.out.println(problem.index);

            } else if (command.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(p, l);
                set.add(problem);
                map.put(p, l);
            } else if (command.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                int l = map.get(p);

                set.remove(new Problem(p, l));
            }
        }
    }
}