import java.util.*;

class Solution {

    class Truck {
        private int weight;
        private int finishTime;

        public Truck(int weight, int finishTime) {
            this.weight = weight;
            this.finishTime = finishTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public int getWeight() {
            return weight;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;

        Deque<Truck> remainTruck = new ArrayDeque<>();
        Deque<Truck> onTruck = new ArrayDeque<>();

        for (int truck_weight : truck_weights) {
            remainTruck.offer(new Truck(truck_weight, 0));
        }

        int remainLength = bridge_length - 1;
        int remainWeight = weight - remainTruck.peek().getWeight();

        remainTruck.peek().setFinishTime(bridge_length);
        onTruck.add(remainTruck.poll());

        while (!onTruck.isEmpty()) {

            if (onTruck.peek().getFinishTime() == time) {
                remainLength++;
                remainWeight += onTruck.peek().getWeight();
                onTruck.poll();
            }

            if (!remainTruck.isEmpty()) {
                if (remainLength > 0 && remainTruck.peek().getWeight() <= remainWeight) {
                    remainLength--;
                    remainWeight -= remainTruck.peek().getWeight();
                    remainTruck.peek().setFinishTime(time + bridge_length);
                    onTruck.add(remainTruck.poll());
                }
            }
            time++;
        }

        return time;
    }
}