import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            for (int i = 0; i < dp.length; i++) {
                dp[i] = Math.min(dp[i], (i > amount ? dp[i - amount] : 0) + price);
            }
        }

        System.out.println(dp[C]);
    }

}

