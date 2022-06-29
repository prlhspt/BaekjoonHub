import java.util.*;

class Solution {

    public int findCount(List<Integer> temp4, int wantScore) {

        if (temp4.size() == 1) {
            if (temp4.get(0) >= wantScore) {
                return 1;
            } else {
                return 0;
            }
        }

        int lo = -1;
        int hi = temp4.size();

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (temp4.get(mid) >= wantScore) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        int result = temp4.size() - hi;
        return result;

    }

    public int[] solution(String[] info, String[] query) {

        Map<String, List<String>> langMap = Map.of("-", List.of("cpp", "java", "python"),
                "cpp", List.of("cpp"), "java", List.of("java"), "python", List.of("python"));
        Map<String, List<String>> groupMap = Map.of("-", List.of("backend", "frontend"),
                "backend", List.of("backend"), "frontend", List.of("frontend"));
        Map<String, List<String>> careerMap = Map.of("-", List.of("junior", "senior"),
                "junior", List.of("junior"), "senior", List.of("senior"));
        Map<String, List<String>> foodMap = Map.of("-", List.of("chicken", "pizza"),
                "chicken", List.of("chicken"), "pizza", List.of("pizza"));

        Map<String, List<Integer>> people = new HashMap<>();

        for (String s : info) {
            String[] split = s.split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append(split[0]).append(split[1]).append(split[2]).append(split[3]);
            people.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(Integer.valueOf(split[4]));
        }

        for (List<Integer> value : people.values()) {
            Collections.sort(value);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int count = 0;
            String[] split = query[i].split(" and ");
            String[] spaceSplit = split[3].split(" ");
            String wantFood = spaceSplit[0];
            int wantScore = Integer.parseInt(spaceSplit[1]);

            StringBuilder sb = new StringBuilder();
            List<String> lang = langMap.get(split[0]);
            for (String s : lang) {
                sb.append(s);
                List<String> group = groupMap.get(split[1]);
                for (String s1 : group) {
                    sb.append(s1);
                    List<String> career = careerMap.get(split[2]);
                    for (String s2 : career) {
                        sb.append(s2);
                        List<String> food = foodMap.get(wantFood);
                        for (String s3 : food) {
                            sb.append(s3);
                            List<Integer> list = people.get(sb.toString());
                            if (list != null) {
                                count += findCount(list, wantScore);
                            }
                            sb.delete(sb.length() - s3.length(), sb.length());
                        }
                        sb.delete(sb.length() - s2.length(), sb.length());
                    }
                    sb.delete(sb.length() - s1.length(), sb.length());
                }
                sb.delete(sb.length() - s.length(), sb.length());
            }

            answer[i] = count;
        }

        return answer;
    }

}
