/**

모든 차량이 단속용 카메라 한번은 만나게

routes[i][0] i번째 차량 고속도로 진입한 지점
routes[i][1] i번째 차량 나간 지점

-5 지점에 카메라를 설치하면 2, 4
-15 지점에 카메라를 설치하면 1, 3

-20 ~ -15

-14 ~ -5

-18 ~ -13

-5 ~ -3

-15에 설치하면 1, 3 커버 됨

-5에 설치하면 2, 4 커버 됨

map에 넣고 +1씩 해서 길 보관

map 최대값부터 정렬한다음에 하나씩 꺼내서 카메라 설치하고 그 구간 다 -1씩 하면 됨

*/

import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] routes) {
        
        List<List<Integer>> list = Arrays.stream(routes)
            .map(u -> Arrays.stream(u)
                .boxed()
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
        
        Map<Integer, Integer> map = new HashMap();
        
        for (int a = 0; a < routes.length; a++) {
            for (int b = routes[a][0]; b < routes[a][1]; b++) {
                map.merge(b, 1, (i, j) -> (i + j));
            }
        }
        
        Map<Integer, Integer> sortedMap = map.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue(),
                        (e1, e2) -> e1, () -> new LinkedHashMap<>()));
            
        System.out.print(sortedMap);
        
        return 0;
    }
}