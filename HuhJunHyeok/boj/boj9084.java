import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 9084. 동전
 */
public class boj9084 {
    /**
     * T: 테스트 케이스의 수, N: 동전의 가지 수(1 <= N <= 20), M: N가지 동전으로 만들어야 할 금액 (1 <= M <= 10_000)
     */
    static int T, N, M;
    /**
     * coins: N가지 동전의 각 금액
     */
    static int[] coins;
//    static int[][] dp;
    static int[] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());

            solution();
        }
        System.out.print(sb.toString());
    }

//    /**
//     * 2차원 dp
//     */
//    public static void solution() {
//        dp = new int[N][M + 1];
//
//        for(int i = 0; i < N; i++) { // 어떤 금액의 동전을 사용하는지
////            dp[i][0] = 1; // 0원을 만드는 방법은 1개.
//            for(int j = 1; j <= M; j++) { // 금액의 범위
//                for(int count = 0; coins[i] * count <= M; count++) { // 동전을 몇 개 사용하는지
//                    if (j == coins[i] * count) {
//                        dp[i][j]++;
//                    } else if (coins[i] * count <= j && i > 0) {
//                        dp[i][j] += dp[i - 1][j - coins[i] * count];
//                    }
//                }
//            }
//        }
//        sb.append(dp[N - 1][M]).append("\n");
//    }

    /**
     * 1차원 dp
     */
    public static void solution() {
        dp = new int[M + 1];

        dp[0] = 1; // 0원을 만드는 방법은 1개.
        for(int coin : coins) { // 어떤 금액의 동전을 사용하는지
            for(int price = coin; price <= M; price++) {
                dp[price] += dp[price - coin];
            }
        }
        sb.append(dp[M]).append("\n");
    }
}
