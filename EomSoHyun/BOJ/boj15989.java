import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[4][n+1];

            for (int i = 0; i < 4; i++) {
                dp[i][0] = 1;
            }
            for (int i = 0; i < n+1; i++) {
                dp[1][i] = 1;
            }

            for (int i = 2; i <= 3; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i-1][j];  // i 선택 X
                    if (j-i >= 0) {
                        dp[i][j] += dp[i][j-i];
                    }
                }
            }

            sb.append(dp[3][n]).append('\n');
        }

        System.out.println(sb);

    }

}
