import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {

            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int goalMoney = Integer.parseInt(br.readLine());

            sb.append(solve(coins, goalMoney)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int[] coins, int goalMoney) {

        int[] dp = new int[goalMoney + 1];
        dp[0] = 1;

        for (int coin = 1; coin < coins.length; coin++) {

            for (int money = coins[coin]; money <= goalMoney; money++) {

                dp[money] += dp[money - coins[coin]];

            }
        }
        return dp[goalMoney];
    }
}
