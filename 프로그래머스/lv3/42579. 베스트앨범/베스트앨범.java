import java.util.*;
import java.util.Map.*;

import static java.util.stream.Collectors.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();

        Map<String, List<List<Integer>>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {

            List<Integer> list = new ArrayList<>();
            list.add(plays[i]);
            list.add(i);
            map.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(list);
        }

        for (String s : map.keySet()) {
            Collections.sort(map.get(s), (o1, o2) -> o2.get(0).compareTo(o1.get(0)));
        }

        Map<String, Integer> sumMap = new HashMap<>();


        for (String s : map.keySet()) {
            int sum = 0;
            List<List<Integer>> lists = map.get(s);
            for (List<Integer> list : lists) {
                sum += list.get(0);
            }
            sumMap.put(s, sum);

        }

        LinkedHashMap<String, Integer> collect = sumMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .collect(toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (e1, e2) -> e1, LinkedHashMap::new
                ));

        for (String s : collect.keySet()) {
            int count = 0;
            for (List<Integer> mapIndex : map.get(s)) {
                if (count < 2) {
                    result.add(mapIndex.get(1));
                }
                count++;

            }
         }

        return result.stream().mapToInt(i -> i).toArray();
    }
}