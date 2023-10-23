import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 17404. RGB거리 2
 */
public class boj17404 {
    /**
     * MAX: dp 배열에서 원소 초기화 시 사용할 값.
     * 집의 수와 집을 칠하는 비용의 최댓값이 모두 1000.
     */
    static final int MAX = 1000 * 1000 + 1;
    /**
     * N: 집의 수, minCost: 문제의 조건에 맞게 모든 집을 칠하는 비용의 최솟값
     */
    static int N, minCost = MAX;
    /**
     * rgbCost: 각 집을 RGB 색으로 칠하는 비용
     * dp: dp 배열
     */
    static int[][] rgbCost, dp;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        rgbCost = new int[N + 1][3];
        dp = new int[N + 1][3];

        // 각 집을 RGB 색으로 칠하는 비용 설정.
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0; j < 3; j++) {
                rgbCost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 집을 칠하는 색으로 구분해서 min값 도출함. ==> 3번 반복.(RGB)
        for(int startIdx = 0; startIdx < 3; startIdx++) {
            for(int i = 0; i < 3; i++) {
                // 1번 집을 칠하는 색에 따라 dp[1][i] 값을 설정.
                // 1번 집을 칠하는 색이 현재 확인하려는 순서의 색과 같으면 rgbCost값 아니면 MAX값.
                if(startIdx == i) {
                    dp[1][i] = rgbCost[1][i];
                } else {
                    dp[1][i] = MAX;
                }
            }

            for(int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgbCost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgbCost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgbCost[i][2];
            }

            // 마지막 집과 1번 집의 색은 달라야 함.
            for(int i = 0; i < 3; i++) {
                if(startIdx != i && minCost > dp[N][i]) {
                    minCost = dp[N][i];
                }
            }
        }

        System.out.println(minCost);
    }
}
