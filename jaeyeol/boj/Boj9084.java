import java.io.*;
import java.util.*;

public class Boj9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[n];
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int price = Integer.parseInt(br.readLine());
            int[] dp = new int[price + 1];
            dp[0] = 1;

            Arrays.stream(coins)
                    .forEach(coin -> IntStream.range(coin, dp.length)
                            .forEach(i -> dp[i] += dp[i - coin]));

            result.append(dp[price]).append("\n");
        }

        System.out.print(result);
    }

}

