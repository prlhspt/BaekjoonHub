import java.util.*;

// String보다 Map이 더 빠르게 동작했다.
// List만 가지고 있는 상태에서 마지막에 정렬하는것보다 map이 가지고 있는 list를 for문으로 돌려서 정렬하는게 훨씬 빠르다. (정답이 갈린 포인트1)
// info 배열이 50000개밖에 안되지만 이분 탐색을 돌려야 한다. (정답이 갈린 포인트2)

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

        Map<String, Map<String, Map<String, Map<String, List<Integer>>>>> people = new HashMap<>();

        for (String s : info) {
            String[] split = s.split(" ");
            people.computeIfAbsent(split[0], k -> new HashMap<>())
                    .computeIfAbsent(split[1], k -> new HashMap<>())
                    .computeIfAbsent(split[2], k -> new HashMap<>())
                    .computeIfAbsent(split[3], k -> new ArrayList<>())
                    .add(Integer.valueOf(split[4]));
        }

        for (Map<String, Map<String, Map<String, List<Integer>>>> value : people.values()) {
            for (Map<String, Map<String, List<Integer>>> stringMapMap : value.values()) {
                for (Map<String, List<Integer>> stringListMap : stringMapMap.values()) {
                    for (List<Integer> list : stringListMap.values()) {
                        Collections.sort(list);
                    }
                }
            }
        }


        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int count = 0;
            String[] split = query[i].split(" and ");
            String[] spaceSplit = split[3].split(" ");
            String wantFood = spaceSplit[0];
            int wantScore = Integer.parseInt(spaceSplit[1]);

            // 언어
            List<String> lang = langMap.get(split[0]);
            for (String s1 : lang) {
                Map<String, Map<String, Map<String, List<Integer>>>> temp1 = people.get(s1);
                if (temp1 != null) {
                    // 직군
                    List<String> group = groupMap.get(split[1]);
                    for (String s2 : group) {
                        Map<String, Map<String, List<Integer>>> temp2 = temp1.get(s2);
                        if (temp2 != null) {
                            // 경력
                            List<String> career = careerMap.get(split[2]);
                            for (String s3 : career) {
                                Map<String, List<Integer>> temp3 = temp2.get(s3);
                                if (temp3 != null) {
                                    // 소울 푸드
                                    List<String> food = foodMap.get(wantFood);
                                    for (String s4 : food) {
                                        List<Integer> temp4 = temp3.get(s4);
                                        if (temp4 != null) {
                                            count += findCount(temp4, wantScore);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        answer[i] = count;
        }

        return answer;
    }

}
