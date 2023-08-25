import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 8394. 악수
 */
public class boj8394 {
    /**
     * n: 회의에 참석한 사람의 수
     */
    static int n;
    /**
     * 사람 수에 따른 악수 횟수 dp 배열
     */
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        /**
         * 악수를 한 번도 하지 않는 것도 경우의 수 중 하나이다.
         * dp[1] = 1, dp[2] = 2
         * dp[3] = 3 = 2 + 1 = dp[2] + dp[1]
         * dp[4] = 5 = 3 + 2 = dp[3] + dp[2]
         * dp[5] = 8 = 5 + 3 = dp[4] + dp[3]
         * 따라서, 점화식은 아래와 같다.
         * dp[n] = dp[n - 1] + dp[n - 2]
         */
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            if(dp[i] > 9) { // 두 자릿수의 값.
                dp[i] %= 10; // 10으로 나머지 연산을 통해 마지막 자리만 남긴다.
            }
        }

        System.out.println(dp[n]);
    }
}
