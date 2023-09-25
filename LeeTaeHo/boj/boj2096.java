import java.io.*;
import java.util.*;

public class boj2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][3];
        int[][][] dp = new int[n][3][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = dp[0][0][1] = map[0][0];
        dp[0][1][0] = dp[0][1][1] = map[0][1];
        dp[0][2][0] = dp[0][2][1] = map[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][0]) + map[i][0];
            dp[i][0][1] = Math.min(dp[i - 1][0][1], dp[i - 1][1][1]) + map[i][0];

            dp[i][1][0] = Math.max(dp[i - 1][0][0], Math.max(dp[i - 1][1][0], dp[i - 1][2][0])) + map[i][1];
            dp[i][1][1] = Math.min(dp[i - 1][0][1], Math.min(dp[i - 1][1][1], dp[i - 1][2][1])) + map[i][1];

            dp[i][2][0] = Math.max(dp[i - 1][1][0], dp[i - 1][2][0]) + map[i][2];
            dp[i][2][1] = Math.min(dp[i - 1][1][1], dp[i - 1][2][1]) + map[i][2];
        }

        System.out.println(Math.max(dp[n - 1][0][0], Math.max(dp[n - 1][1][0], dp[n - 1][2][0])) + " " + Math.min(dp[n - 1][0][1], Math.min(dp[n - 1][1][1], dp[n- 1][2][1])));
    }
}