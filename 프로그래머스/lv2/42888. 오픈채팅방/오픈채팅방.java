import java.util.*;

class Solution {

    public String[] solution(String[] record) {

        List<String> list = new ArrayList<>();
        Map<String, String> name = new HashMap<>();

        for (String r : record) {
            String[] s = r.split(" ");
            if (s[0].equals("Change")) {
                name.put(s[1], s[2]);
            } else if (s[0].equals("Enter")){
                name.put(s[1], s[2]);
            }
        }

        for (String r : record) {
            String[] s = r.split(" ");
            if (s[0].equals("Enter")) {
                list.add(name.get(s[1])+"님이 들어왔습니다.");
            } else if (s[0].equals("Leave")) {
                list.add(name.get(s[1])+"님이 나갔습니다.");
            }
        }

        return list.toArray(new String[0]);
    }
}