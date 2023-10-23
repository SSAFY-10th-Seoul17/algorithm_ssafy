import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj16719 {

    static String input;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();
        check = new boolean[input.length()];
        solve(0, input.length());
        System.out.println(sb);
    }

    private static void solve(int start, int end) {

        if (start == end) {
            return;
        }
        int minIdx = 0;
        int min = 100;
        for (int i = start; i < end; i++) {
            if (min > input.charAt(i)) {
                min = input.charAt(i);
                minIdx = i;
            }
        }
        check[minIdx] = true;
        check();

        solve(minIdx + 1, end);
        solve(start, minIdx);
    }

    private static void check() {
        for (int idx = 0; idx < check.length; idx++) {
            if (check[idx]) {
                sb.append(input.charAt(idx));
            }
        }
        sb.append("\n");
    }


}
