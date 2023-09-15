package BOJ;

import java.io.*;

public class boj08394 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Integer.parseInt(br.readLine());

        long[] dp = new long[(int) Math.max(3, n+1)];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10;
        }

        System.out.println(dp[(int) n]);
    } // end of main
} // end of class