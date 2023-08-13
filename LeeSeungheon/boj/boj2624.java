import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2624 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] money = new int[K + 1][2];

        for (int mon = 1; mon <= K; mon++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            money[mon][0] = Integer.parseInt(st.nextToken());
            money[mon][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(money, T));
    }

    private static int solve(int[][] money, int T) {
        int[][] dp = new int[money.length][T + 1];
        dp[0][0] = 1;

        for (int unit = 1; unit < money.length; unit++) {
            for (int pay = 0; pay <= T; pay++) {

                dp[unit][pay] = dp[unit - 1][pay];

                if (pay < money[unit][0]) {
                    continue;
                }
                for (int i = 1; i <= money[unit][1] && pay - i * money[unit][0] >= 0; i++) {
                    dp[unit][pay] += dp[unit - 1][pay - i * money[unit][0]];
                }
            }
        }

        return dp[money.length-1][T];
    }
}
