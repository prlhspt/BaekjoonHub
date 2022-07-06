/*
return : 이중 우선순위 큐의 최댓값, 최솟값
idea : 이진검색트리로 구현
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String w = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (w.equals("I")) {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else {
                    if (map.size() == 0) {
                        continue;
                    }

                    int num = (n == 1) ? map.lastKey() : map.firstKey();

                    if (map.put(num, map.get(num) - 1) == 1) {
                        map.remove(num);
                    }
                }
            }

            System.out.println(map.size() == 0 ? "EMPTY" : map.lastKey() + " " + map.firstKey());

        }

    }
}