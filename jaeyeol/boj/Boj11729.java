import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11729 {

    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = (2 << n - 1) - 1;

        if (n <= 20) {
            hanoi(n, 1, 2, 3);
            System.out.println(count + "\n" + result);
        } else {
            System.out.println(count);
        }
    }

    private static void hanoi(int n, int start, int mid, int end) {
        if (n == 1) {
            result.append(start).append(" ").append(end).append("\n");
            return;
        }

        hanoi(n - 1, start, end, mid);
        result.append(start).append(" ").append(end).append("\n");
        hanoi(n - 1, mid, start, end);
    }

}
