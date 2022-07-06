import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

class Main {

    static class Stone {
        int weight;
        int value;

        public Stone(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {

        long answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        TreeMap<Stone, Integer> stoneMap = new TreeMap<>((o1, o2) -> {
            if (o1.value == o2.value) {
                return Integer.compare(o2.weight, o1.weight);
            }
            return Integer.compare(o1.value, o2.value);
        });
        TreeMap<Integer, Integer> bagMap = new TreeMap<>();


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Stone stone = new Stone(m, v);
            stoneMap.put(stone, stoneMap.getOrDefault(stone, 0) + 1);
        }

        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(br.readLine());
            bagMap.put(c, bagMap.getOrDefault(c, 0) + 1);
        }

        while (!stoneMap.isEmpty() && !bagMap.isEmpty()) {

            Stone last = stoneMap.lastKey();
            if (stoneMap.put(last, stoneMap.get(last) - 1) == 1) {
                stoneMap.remove(last);
            }

            Integer num = bagMap.ceilingKey(last.weight);
            if (num != null) {

                if (bagMap.put(num, bagMap.get(num) - 1) == 1) {
                    bagMap.remove(num);
                }
                answer += last.value;
            }
        }
        System.out.println(answer);
    }
}