import java.util.*;

class Main {

    static class Truck {
        int weight;
        int time;

        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 다리의 길이
        int w = sc.nextInt();
        // 다리의 최대 하중
        int l = sc.nextInt();

        int time = 0;

        Queue<Truck> queue = new ArrayDeque<>();
        List<Truck> onRoad = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            queue.offer(new Truck(sc.nextInt(), w));
        }

        while (!queue.isEmpty() || !onRoad.isEmpty()) {
            Truck now = queue.peek();

            for (int i = 0; i < onRoad.size(); i++) {
                onRoad.get(i).time--;

                if (onRoad.get(i).time == 0) {
                    l += onRoad.get(i).weight;
                    onRoad.remove(i);
                    i--;
                }
            }
            if (now != null && l - now.weight >= 0 && onRoad.size() - w < 0) {
                l -= now.weight;
                onRoad.add(now);
                queue.poll();
            }
            time++;
        }
        System.out.println(time);
    }
}