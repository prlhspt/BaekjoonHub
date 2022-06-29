import java.util.*;
import java.util.stream.*;

public class Main {

    static int n;
    static int m;
    static int[] woods;

    static boolean check(long mid) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (woods[i] > mid) {
                sum += woods[i] - mid;
            }
        }
        return sum >= m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        woods = new int[n];

        for (int i = 0; i < n; i++) {
            woods[i] = sc.nextInt();
        }

        long low = 0, high = IntStream.of(woods).max().getAsInt() + 1;

        while (low + 1 < high) {
            long mid = (low + high) / 2;

            if (check(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        System.out.print(low);
    }
}