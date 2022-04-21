import java.util.*;

public class Main {

    static void removeLiar(int[] arr, int sum, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    arr[i] = -1;
                    arr[j] = -1;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        int sum = 0;

        int n = 9;
        int[] arr = new int[n];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        removeLiar(arr, sum, n);

        Arrays.sort(arr);

        for (int i = 2; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
