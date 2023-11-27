package study10월3주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj15989_123더하기4 {
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 1; i <= 3; i++)
                for (int j = i; j <= n; j++)
                    dp[j] += dp[j - i];
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
    }
}