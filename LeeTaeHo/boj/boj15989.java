import java.io.*;

public class boj15989 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[6][10001];

        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 2;
        dp[2][2] = 1;
        dp[3][3] = 1;

        for (int i = 3; i >= 1; i--) {
            for (int j = 4; j <= 10000; j++) {
                dp[i][j] += dp[i][j - i] + dp[i + 1][j - i] + dp[i + 2][j - i];
            }
        }

        for (int testCase = 1; testCase <= t; testCase++) {
            int n = Integer.parseInt(br.readLine());

            int result = dp[1][n] + dp[2][n] + dp[3][n];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}