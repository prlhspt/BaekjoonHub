import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(String str1, String str2) {

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        char[] chars1 = str1.toCharArray();
        for (int i = 0; i < chars1.length - 1; i++) {
            String s = (String.valueOf(chars1[i]) + chars1[i + 1]).replaceAll("[^a-z]", "");
            if (s.length() == 2) {
                list1.add(s);
            }
        }

        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars2.length - 1; i++) {
            String s = (String.valueOf(chars2[i]) + chars2[i + 1]).replaceAll("[^a-z]", "");
            if (s.length() == 2) {
                list2.add(s);
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int index1 = 0;
        int index2 = 0;

        List<String> intersection = new ArrayList<>();
        List<String> union = new ArrayList<>();

        while (index1 < list1.size() && index2 < list2.size()) {
            String s1 = list1.get(index1);
            String s2 = list2.get(index2);
            if (s1.compareTo(s2) == 0) {
                intersection.add(s1);
                union.add(s1);
                index1++;
                index2++;
            } else if (s1.compareTo(s2) > 0) {
                union.add(s2);
                index2++;
            } else {
                union.add(s1);
                index1++;
            }
        }

        for (int i = index1; i < list1.size(); i++) {
            union.add(list1.get(i));
        }

        for (int i = index2; i < list2.size(); i++) {
            union.add(list2.get(i));
        }


        double answer = (double) intersection.size() / (double) union.size() * 65536;
        
        if (union.size() == 0 && intersection.size() == 0) {
            answer = 65536;
        }

        return (int) answer;

    }

}