import java.io.*;
import java.util.*;

public class boj1106 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[c + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int price = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= c; j++) {
                dp[j] = Math.min(dp[j], j <= amount ? price : dp[j - amount] + price );
            }
        }
        System.out.println(dp[c]);
    }
}
