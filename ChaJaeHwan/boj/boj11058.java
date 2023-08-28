import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11058 {

    static int N;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i-1] + 1;
            if (i > 6) {
                for (int j = 2; j <= 5 ; j++) {
                    dp[i] = Math.max(dp[i], dp[i - (j+1)] * j);
                }
            }
        }
        System.out.println(dp[N]);
    }


}
