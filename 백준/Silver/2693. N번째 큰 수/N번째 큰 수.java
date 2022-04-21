import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int a = 0; a < n; a++) {
            int[] arr = new int[10];
            for (int b = 0; b < 10; b++) {
                arr[b] = sc.nextInt();
            }
            Arrays.sort(arr);
            System.out.println(arr[7]);
        }
    }
}