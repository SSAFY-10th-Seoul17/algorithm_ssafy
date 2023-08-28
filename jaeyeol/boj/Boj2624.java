import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2624 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int price = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[price + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int coin = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            for (int j = price; j >= coin; j--) {
                for (int k = coin * Math.min(j / coin, amount); k > 0; k -= coin) {
                    dp[j] += dp[j - k];
                }
            }
        }

        System.out.println(dp[price]);
    }

}

