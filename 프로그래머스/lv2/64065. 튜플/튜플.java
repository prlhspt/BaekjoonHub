import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

class Solution {
    public int[] solution(String s) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder(s);
        while (!sb.toString().equals("{")) {
            int right = sb.indexOf("}");
            int left = right - 1;
            while (left > 0) {
                if (sb.charAt(left) == '{') {
                    left++;
                    break;
                }
                left--;
            }
            String substring = sb.substring(left, right);
            String[] splits = substring.split(",");

            List<Integer> temp = new ArrayList<>();
            for (String split : splits) {
                temp.add(Integer.parseInt(split));
            }
            list.add(temp);
            sb.delete(left-1, right+2);
        }

        list.sort(Comparator.comparingInt(List::size));
        HashSet<Integer> set = new HashSet<>();
        for (List<Integer> intList : list) {
            for (Integer i : intList) {
                if (!set.contains(i)) {
                    result.add(i);
                    set.add(i);
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}