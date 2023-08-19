import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11058 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 4; i <= n; i++) {
            long print = dp[i - 3];
            long buffer = dp[i - 3];
            for (int j = i; j <= n; j++) {
                dp[j] = Math.max(dp[j], print += buffer);
            }
        }

        System.out.println(dp[n]);
    }

}
