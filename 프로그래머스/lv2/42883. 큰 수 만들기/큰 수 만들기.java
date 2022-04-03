import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String number, int k) {
        int[] n = Arrays.stream(number.split(""))
                .mapToInt(i -> Integer.parseInt(i)).toArray();
        int nLength = n.length;

        List<Integer> list = new ArrayList<>();

        list.add(n[0]);

        for (int a = 1; a < nLength; a++) {

            if (k == 0) {
                for (int b = a; b < nLength; b++) {
                    list.add(n[b]);
                }
                break;
            }

            if (n[a] > list.get(list.size() - 1)) {
                // list 돌면서 n[a]보다 작은거 다 지워야 함

                int index = list.size() - 1;
                while (index >= 0) {
                    if (list.get(index) < n[a]) {
                        list.remove(index);
                        k--;
                    }

                    if (k == 0) {
                        break;
                    }

                    index--;
                }
            }
            list.add(n[a]);
        }

        for (int i = 0; i < k; i++) {
            list.remove(list.size() - 1);
        }

        return list.stream().map(i -> String.join("", String.valueOf(i)))
                .collect(Collectors.joining());
    }
}