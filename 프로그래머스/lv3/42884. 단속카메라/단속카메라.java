/**
 end가 빠른 순서대로 설치하면 됨
 */

import java.util.*;
import java.util.stream.*;

class Solution {

    class Road implements Comparable<Road> {
        private int start;
        private int end;

        public Road (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart()  {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        @Override
        public int compareTo(Road o) {
            return (this.end < o.end) ?
                    -1 : ((this.end == o.end) ?
                    0 : 1);
        }

    }

    public int solution(int[][] routes) {
        int answer = 0;

        List<Road> list = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {
            Road road = new Road(routes[i][0], routes[i][1]);
            list.add(road);
        }
        Collections.sort(list);

        // 하나 꺼내서 하나 설치하고, 여기에 찍힐 수 있는 차 전부 제거하기
        while (!list.isEmpty()) {
            Road road = list.get(0);
            list.remove(0);
            answer++;

            int index = 0;
            while (index < list.size()) {
                if (list.get(index).getStart() <= road.getEnd() && list.get(index).getEnd() >= road.getStart()) {
                    list.remove(index);
                } else {
                    index++;
                }
            }
        }
        return answer;
    }
}