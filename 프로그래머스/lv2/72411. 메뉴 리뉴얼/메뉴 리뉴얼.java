import java.util.*;
import java.util.stream.Collectors;

class Solution {

    List<String> ORDERS;
    String MENU;
    String[] PICK;
    Map<String, Integer> MAP;
    List<String> RESULT = new ArrayList<>();

    public void comb(int k, int cur, int r) {
        if (k == r) {
            Arrays.sort(PICK);
            String join = String.join("", PICK);
            MAP.merge(join, 1, (oldValue, newValue) -> MAP.get(join) + 1);
            return;
        }

        for (int i = cur; i < MENU.length(); i++) {
            String[] copy = Arrays.copyOf(PICK, PICK.length);
            PICK[k] = String.valueOf(MENU.charAt(i));
            comb(k + 1, i + 1, r);
            PICK = copy;

        }

    }

    public String[] solution(String[] orders, int[] course) {

        ORDERS = Arrays.asList(orders);

        for (int i : course) {

            MAP = new HashMap<>();

            for (String order : orders) {

                MENU = order;
                PICK = new String[i];
                comb(0, 0, i);

            }

            List<Map.Entry<String, Integer>> collect = MAP.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            
            int count = 2;

            for (Map.Entry<String, Integer> entry : collect) {
                if (entry.getValue() < count) {
                    break;
                }
                RESULT.add(entry.getKey());
                count = entry.getValue();
            }
        }

        Collections.sort(RESULT);

        return RESULT.toArray(String[]::new);
    }

}