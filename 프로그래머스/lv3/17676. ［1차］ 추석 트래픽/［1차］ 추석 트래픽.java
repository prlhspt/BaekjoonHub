import java.util.*;

class Solution {
    class Traffic {
        public int start;
        public int end;

        public Traffic(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "\n start : " + start + ", end : " + end;
        }
    }

    public int solution(String[] lines) {

        if (lines.length == 1) {
            return 1;
        }

        int answer = 0;

        List<Traffic> list = new ArrayList<>();

        for (String line : lines) {
            String[] l = line.split(" ");

            String second = l[1].split("\\.")[0];
            String milliSecond = l[1].split("\\.")[1];
            String[] hms = second.split(":");

            int convertSecond = (Integer.parseInt(hms[2]) + Integer.parseInt(hms[1]) * 60 + Integer.parseInt(hms[0]) * 60 * 60) * 1000;

            int end = convertSecond + Integer.parseInt(milliSecond);

            String[] differences = l[2].replace("s", "").split("\\.");
            int difference = 0;
            if (differences.length == 1) {
                difference = Integer.parseInt(differences[0]) * 1000;
            } else {
                difference = Integer.parseInt(differences[0]) * 1000 + Integer.parseInt(differences[1]);
            }

            int start = end - difference;

            list.add(new Traffic(start ,end));
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.end == o2.end) {
                return Double.compare(o1.start, o2.start);
            } else {
                return Double.compare(o1.end, o2.end);
            }
        });

        for (int i = 0; i < list.size() - 1; i++) {
            int index = i + 1;
            int count = 1;

            while (true) {
                if (index == list.size()) break;
                if (list.get(i).end + 999 > list.get(index).start) {
                    count++;
                }
                index++;
            }
            answer = Math.max(answer, count);

        }

        return answer;
    }
}