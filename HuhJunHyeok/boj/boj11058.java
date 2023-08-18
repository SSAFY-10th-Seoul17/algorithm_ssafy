import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 11058. 크리보드
 */
public class boj11058 {
    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];

        for(int i = 1; i <= N; i++) {
            long tempMax = dp[i - 1] + 1 > i ? dp[i - 1] + 1 : i;

            int multiplyCnt = 2;
            for(int j = i - 3; j >= 1; j--) {
                tempMax = tempMax > dp[j] * multiplyCnt ? tempMax : dp[j] * multiplyCnt;
                // i - 3일 때, 1번 더한 것.( * 2) i - 4면, 2번 더할 수 있음.( * 3) 따라서 ++ 연산.
                multiplyCnt++;
            }
            dp[i] = tempMax;
        }

        System.out.println(dp[N]);
    }
}
