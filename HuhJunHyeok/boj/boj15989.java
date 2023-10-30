import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 15989. 1, 2, 3 더하기 4
 */
public class boj15989 {
    /**
     * n: 만들려고 하는 정수
     */
    static int n;
    /**
     * dp: dp 배열. dp[k][n]은 현재 n번 인덱스에서 사용하는 정수를 더할 때 k값을 만들 수 있는 경우의 수.
     * 10_001 ==> 정수 n의 값이 10_000 이하의 양수
     * 3 ==> 1~3의 3가지 정수의 합. 0인덱스 1, 1인덱스 2, 2인덱스 3
     */
    static int[][] dp = new int[10_001][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        dp[1][0] = 1; // 1
        dp[2][0] = 1; // 1 + 1
        dp[2][1] = 1; // 2
        dp[3][0] = 1; // 1 + 1 + 1
        dp[3][1] = 1; // 1 + 2
        dp[3][2] = 1; // 3
        for(int i = 4; i <= 10_000; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
            dp[i][2] = dp[i - 3][0] + dp[i - 3][1] + dp[i - 3][2];
        }

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());

            sb.append(dp[n][0] + dp[n][1] + dp[n][2]).append("\n");
        }

        System.out.print(sb.toString());
    }
}
