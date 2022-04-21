import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] fibo = new int[n];

        if (n == 0) {
            System.out.print(0);
        } else if (n == 1 || n == 2) {
            System.out.print(1);
        } else if (n == 3) {
            System.out.print(2);
        } else {
            fibo[0] = 1;
            fibo[1] = 1;
            fibo[2] = 2;
            for (int i = 3; i < n; i++) {
                fibo[i] = fibo[i-1] + fibo[i-2];
            }
            System.out.print(fibo[n-1]);
        }
    }
}