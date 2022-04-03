import java.util.*;

class Solution {
    public String solution(int[] numbers) {

        String s = Arrays.stream(Arrays.stream(numbers)
                        .mapToLong(i -> i).boxed().toArray(value -> new Long[value]))
                .sorted(((o1, o2) ->
                        Long.parseLong(String.valueOf(o1).repeat(4).substring(0, 4)) <
                                Long.parseLong(String.valueOf(o2).repeat(4).substring(0, 4))
                                ? 1
                                : Long.parseLong(String.valueOf(o1).repeat(4).substring(0, 4)) ==
                                Long.parseLong(String.valueOf(o2).repeat(4).substring(0, 4))
                                    ? 0
                                    : -1))
                .map(obj -> String.valueOf(obj))
                .reduce((a, b) -> a.concat(b))
                .get();

        if (s.charAt(0) == '0') {
            return "0";
        } else {
            return s;
        }
    }
}