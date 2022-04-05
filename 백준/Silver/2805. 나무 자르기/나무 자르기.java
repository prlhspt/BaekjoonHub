import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] woods = new int[n];

        for (int i = 0; i < n; i++) {
            woods[i] = sc.nextInt();
        }

        long low = 0;
        long high = IntStream.of(woods).max().getAsInt() + 1;
        long mid;

        while (low + 1 < high) {
            mid = (low + high) / 2;

            long woodLength = 0;
            for (int i = 0; i < n; i++) {
                if (woods[i] - mid > 0) {
                    woodLength += (woods[i] - mid);
                }
            }

            // T
            if (woodLength >= m) {
                low = mid;
            }
            // F
            else {
                high = mid;
            }

        }
        System.out.print(low);
    }
}