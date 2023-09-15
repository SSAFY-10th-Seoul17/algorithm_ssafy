import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 1106. 호텔
 */
public class boj1106 {
    /**
     * C: 늘려야 하는 고객의 최소 인원
     * N: 홍보할 수 있는 도시의 수
     * minInvestCost: 호텔의 고객을 적어도 C명 늘리기 위해 투자해야 하는 돈의 최솟값
     */
    static int C, N, minInvestCost = Integer.MAX_VALUE;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        /**
         * 0 번 인덱스 사용X.
         * 각 도시에서 홍보할 때 대는 비용과 그 비용으로 얻을 수 있는 고객의 수가 주어진다.
         * 이 값은 100보다 작거나 같은 자연수라고 문제에 명시되어 있다.
         * 또한, 문제의 요구는 최소한의 비용으로 목표치 이상의 고객 유치이다.
         * 따라서, dp 배열의 크기는 C + 101 이다.
         */
        int size = C + 101;
        dp = new int[size];
        for(int i = 1; i < size; i++) {
            dp[i] = 1_000_000_000; // Integer.MAX_VALUE 로 설정하면 오버플로우 발생.
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int adCost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            for(int j = customer; j < size; j++) {
                dp[j] = dp[j] < dp[j - customer] + adCost ? dp[j] : dp[j - customer] + adCost;
            }
        }

        for(int i = C; i < size; i++) {
            minInvestCost = minInvestCost < dp[i] ? minInvestCost : dp[i];
        }

        System.out.println(minInvestCost);
    }
}
