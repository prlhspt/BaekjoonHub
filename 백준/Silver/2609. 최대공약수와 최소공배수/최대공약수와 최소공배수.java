import java.util.*;

public class Main {

    static int findGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return findGcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int gcd = findGcd(m, n);

        int lcm = m * n / gcd;

        System.out.println(gcd);
        System.out.println(lcm);

    }
}

