package BOJ;

import java.io.*;
import java.util.*;

public class boj11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        dp[5] = 5;

        for(int i = 5; i < N + 1; i++){
            long sum = 0;
            for(int j = 3; j <= i; j++){
                sum = Math.max(dp[i - j] * (j - 1), sum);
            }
            dp[i] = Math.max(dp[i - 1] + 1,sum);
        }
        System.out.println(dp[N]);
    }
}
