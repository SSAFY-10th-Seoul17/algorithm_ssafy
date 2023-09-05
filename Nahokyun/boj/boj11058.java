package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
크리보드
 */
public class boj11058 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[101];

        for (int i = 1; i <= 6; i++) {
            dp[i] = i;
        }

        for (int i = 7; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[i] = Math.max(dp[i], dp[i - 3 - j] * (j + 2));
            }
        }
        System.out.println(dp[n]);
    }
}

