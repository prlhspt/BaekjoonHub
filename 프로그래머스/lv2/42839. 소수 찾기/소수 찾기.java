import java.util.*;
import java.util.stream.*;

class Solution {

    Set<Integer> primeSet = new HashSet<>();

    boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            primeCheck(output, r);
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

    void primeCheck(int[] output, int r) {
        output = Arrays.copyOfRange(output, 0, r);
        int joinOutput = Integer.parseInt(
                Arrays.stream(output)
                        .mapToObj(i -> String.valueOf(i))
                        .collect(Collectors.joining()));
        boolean primeFlag;
        if (joinOutput < 2) {
            primeFlag = false;
        } else {
            primeFlag = isPrime(joinOutput);
        }


        if (primeFlag == true) {
            primeSet.add(joinOutput);
        }
    }

    public int solution(String numbers) {
        int[] intNumbers = Arrays.stream(numbers.split(""))
                .mapToInt(i -> Integer.parseInt(i)).toArray();

        int n = intNumbers.length;

        int[] output = new int[n];
        boolean[] visited = new boolean[n];


        for (int i = 1; i <= n; i++) {
            permutation(intNumbers, output, visited, 0, n, i);
        }

        return primeSet.size();
    }
}