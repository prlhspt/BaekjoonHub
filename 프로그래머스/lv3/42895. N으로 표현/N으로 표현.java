/**
5를 1개 쓰기
5

5를 2개 쓰기
55
5 + 5 = 10
5 - 5 = 0
5 * 5 = 25
5 / 5 = 1

5를 3개 쓰기
555
(5 1개) 사칙연산 (5 2개)
(5 2개) 사칙연산 (5 1개)

5를 4개 쓰기
5555
(5 1개) 사칙연산 (5 3개)
(5 2개) 사칙연산 (5 2개)
(5 3개) 사칙연산 (5 1개)

5를 5개
(5 1개) (5 4개)
(5 2개) (5 3개)
(5 3개) (5 2개)
(5 4개) (5 1개)
*/
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();

        if (N == number) {
            return 1;
        }

        // 계산 편의성, 인덱스 맞추기
        Set<Integer> set = new HashSet<>();
        list.add(set);

        // 1번
        int index = 1;
        set = new HashSet<>();
        set.add(N);
        list.add(set);

        while (true) {
            if (index > 8) {
                return -1;
            }

            index++;

            set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < index; i++) {
                sb.append(N);
            }
            set.add(Integer.parseInt(sb.toString()));

            for (int i = 1; i < index; i++) {
                Iterator<Integer> oneIt = list.get(i).iterator();
                while (oneIt.hasNext()) {
                    int one = oneIt.next();
                    Iterator<Integer> anotherIt = list.get(index-i).iterator();
                    while (anotherIt.hasNext()) {
                        int another = anotherIt.next();
                        set.add(one + another);
                        set.add(one - another);
                        set.add(one * another);
                        if (another != 0) {
                            set.add(one / another);
                        }
                    }
                }
            }

            if (set.contains(number)) {
                return index;
            }
            list.add(set);
        }
    }
}