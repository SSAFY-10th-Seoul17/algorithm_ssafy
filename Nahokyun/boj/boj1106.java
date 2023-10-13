package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class boj1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n + 1][2];
        int[][] dp = new int[n + 1][C + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());// 비용
            arr[i][1] = Integer.parseInt(st.nextToken());// 인원
        } // 입력 종료

        for (int i = 1; i <= C; i++) {
            dp[1][i] = ((i-1) / arr[1][1] + 1) * arr[1][0];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= C; j++) {
                if (j > arr[i][1]) {
                    dp[i][j] = Math.min(dp[i][j-arr[i][1]]+arr[i][0], Math.min(dp[i - 1][j - arr[i][1]] + arr[i][0], dp[i - 1][j]));
                }else {
                    dp[i][j]=Math.min(dp[i-1][j], arr[i][0]);
                }
            }
        }

        System.out.println(dp[n][C]);

    }
}

