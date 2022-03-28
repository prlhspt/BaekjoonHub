import java.util.Scanner;

public class Main {

    // 인덱스 순서는 지키면서 n개중에 r개 뽑기
    static void permutation(int[] arr, int[] output,
                     boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                output[depth] = 0;
                visited[i] = false;
            }
        }
    }

    static void print(int[] arr, int r) {
        for (int i = 0; i < r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }
        int[] output = new int[n];
        boolean[] visited = new boolean[n];

        permutation(arr, output, visited, 0, n, n);
    }
}