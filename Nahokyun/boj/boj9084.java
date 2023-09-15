package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
동전
 */
public class boj9084 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < t; test++) {

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] cost = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            //입력 종료
            int[][] dp = new int[n + 1][m + 1];

            for (int i = 0; cost[1] * i <= m; i++) {
                dp[1][cost[1] * i] = 1;
            }


            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    if (j >= cost[i]) {
                        for (int k = 0; k * cost[i] <= j; k++) {
                            dp[i][j] += dp[i - 1][j - k * cost[i]];
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            sb.append(dp[n][m]).append('\n');

        }
        System.out.println(sb.toString());
    }
}

