import java.io.*;
import java.util.*;

public class Main {
    public static int[][] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        coins = new int[k + 1][2];
        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken()), num = Integer.parseInt(st.nextToken());
            coins[i][0] = coin;
            coins[i][1] = num;
        }
        Arrays.sort(coins, (c1, c2) -> c1[0] - c2[0]);
        int[][] dp = new int[k + 1][t + 1];
        dp[0][0] = 1;   // 0원 초기화
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= coins[i][1]; j++) {    // 사용 동전 개수
                int add = coins[i][0] * j;
                for (int l = 0; l <= t; l++) {
                    if (add + l > t) break;  // 범위
                    dp[i][add + l] += dp[i - 1][l];
                }
            }
        }
        System.out.println(dp[k][t]);
        br.close();
    }
}
