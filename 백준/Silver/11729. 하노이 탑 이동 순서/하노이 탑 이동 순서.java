import java.io.*;
import java.util.Scanner;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static StringBuilder sb = new StringBuilder();
    static void hanoi(int a, int b, int n) throws IOException {
        if (n == 1) {
            sb.append(a+" "+b+"\n");
            return;
        }
        hanoi(a, 6-a-b, n-1);
        sb.append(a+" "+b+"\n");
        hanoi(6-a-b, b, n-1);
    }

    public static void main(String[] args) throws IOException {


        int a = Integer.parseInt(br.readLine());

        sb.append(((1 << a) - 1) + "\n");

        hanoi(1, 3, a);

        System.out.println(sb);

    }
}