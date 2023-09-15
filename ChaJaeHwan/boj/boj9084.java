import java.io.*;
import java.util.*;

public class boj9084 {

    static int T, N, M;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];

            dp[0] = 1;
            for (int coin : coins) {
                for (int i = 1; i <= M; i++) {
                    if (i - coin >= 0) {
                        dp[i] += dp[i - coin];
                    }
                }
            }


            System.out.println(dp[M]);
        }
    }
}
