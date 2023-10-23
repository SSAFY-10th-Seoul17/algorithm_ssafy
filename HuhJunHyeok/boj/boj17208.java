import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 17208. 카우버거 알바생
 */
public class boj17208 {
    /**
     * N: 주문의 수 (1 <= N <= 100)
     * M: 주방에 남은 치즈버거의 수 (1 <= M <= 300)
     * K: 주방에 남은 감자튀김의 수 (1 <= K <= 300)
     */
    static int N, M, K;
    /**
     * burgers, fries: 각각 인덱스의 해당하는 주문서에서 요청된 주문 건수
     */
    static int[] burgers, fries;
    /**
     * dp: dp 배열.
     */
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        burgers = new int[N];
        fries = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            burgers[i] = Integer.parseInt(st.nextToken());
            fries[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j][k]의 값은 i번째 주문, j개의 버거 남음, k개의 감튀 남음일 때의 최적값을 의미.
        dp = new int[N + 1][M + 1][K + 1];
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                for(int k = 0; k <= K; k++) {
                    dp[i][j][k] = -1; // dp 초기화.
                }
            }
        }

        System.out.println(knapsack(0, M, K));
    }

    public static int knapsack(int orderIdx, int burger, int fry) {
        if(orderIdx == N) { // 마지막 주문까지 확인한 다음이라 확인할 주문이 없는 경우
            return 0;
        }

        if(dp[orderIdx][burger][fry] >= 0) { // 이미 계산된 값이 있는 경우
            return dp[orderIdx][burger][fry];
        }

        // 확인한 주문을 처리하는 경우와 넘기는 경우 2가지.
        if(burgers[orderIdx] <= burger && fries[orderIdx] <= fry) { // 주문을 처리할 수 있는 경우
            dp[orderIdx][burger][fry] = knapsack(orderIdx + 1, burger - burgers[orderIdx], fry - fries[orderIdx]) + 1;
        }
        return dp[orderIdx][burger][fry] = Math.max(dp[orderIdx][burger][fry], knapsack(orderIdx + 1, burger, fry));
    }
}
