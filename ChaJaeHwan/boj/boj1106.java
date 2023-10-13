import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1106 {

    static int C, N, cost, customer;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[C + 101];
        Arrays.fill(dp, 100*1000+1);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            for (int j = customer; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);


//        for (int i = 1; i <= C; i++) {
//            for (int j = 1; j < i; j++) {
//                dp[i] = Math.max(dp[i], dp[i-j] + dp[j]);
//            }
//        }
//
//        System.out.println(Arrays.toString(dp));
//        for (int i = 1; i <= C; i++) {
//            if (dp[i] >= C) {
//                System.out.println(i);
//                break;
//            }
//        }
    }
}
