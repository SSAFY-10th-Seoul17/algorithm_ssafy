import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solve(N));
    }

    private static long solve(int N) {
        long[] dp = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;

            int j = 3;
            while (i + j <= N && (j + 1) * dp[i - 1] <= j * dp[i]) {

                if (dp[i + j] < dp[i - 1] * (j + 1)) {
                    dp[i + j] = dp[i - 1] * (j + 1);
                }
                j++;
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[N];
    }
}
