import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 4811. 알약
 */
public class boj4811 {
    /**
     * N: 병에 들어있는 약의 개수
     */
    static int N;
    /**
     * dp[x][y]: 알약 한 조각이 x개, 반 조각이 y개 있는 상태에서의 가능한 서로 다른 문자열의 수.
     * 최종 답은 dp[N][0]이다.
     * 알약 반 조각이 없는 상황. 즉, y == 0인 상황이면
     * dp[x][y] = dp[x - 1][y + 1]
     * 알약 반 조각이 있는 상황. 즉, y > 0인 상황이면
     * dp[x][y] = dp[x - 1][y + 1] + dp[x][y - 1]
     */
    static long[][] dp = new long[31][31];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1; i <= 30; i++) {
            dp[0][i] = 1; // 한 조각이 없고 반 조각만 있으면 HHH... 의 한 가지 경우.
        }
        for(int i = 1; i <= 30; i++) {
            for(int j = 0; j < 30; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
                }
            }
        }

        while((N = Integer.parseInt(br.readLine())) != 0) {
            sb.append(dp[N][0]).append("\n");
        }

        System.out.print(sb.toString());
    }
}
