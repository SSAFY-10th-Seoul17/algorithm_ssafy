import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj14606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        System.out.println((long)num * (num - 1) / 2);
    }

    private static int solve(int num) {
        int[] dp = new int[num + 1];
        dp[1] = 0; // dp[2] = 1  // dp[3] = dp[2] + dp[1] + 2

        for (int i = 2; i < dp.length; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, dp[j] + dp[i - j] + (j * (i - j)));
            }
            dp[i] = max;
        }
        return dp[num];
    }
}
