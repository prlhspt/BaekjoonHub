import java.math.BigInteger;
import java.util.*;

public class Main {

    static int k;
    static int n;
    static BigInteger[] lines;

    static int check(BigInteger mid) {

        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < k; i++) {
            sum = sum.add(lines[i].divide(mid));
        }
        return sum.compareTo(BigInteger.valueOf(n));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();

        lines = new BigInteger[k];
        BigInteger max = BigInteger.valueOf(0);
        for (int i = 0; i < k; i++) {
            lines[i] = BigInteger.valueOf(sc.nextInt());
            if (max.compareTo(lines[i]) == -1) {
                max = lines[i];
            }
        }

        // 최댓값으로 자르면 무조건 F
        BigInteger low = BigInteger.valueOf(0), high = max.add(BigInteger.valueOf(1));

        while (low.add(BigInteger.valueOf(1)).compareTo(high) == -1) {
            BigInteger mid = low.add(high).divide(BigInteger.valueOf(2));

            if (check(mid) >= 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        System.out.print(low);
    }
}