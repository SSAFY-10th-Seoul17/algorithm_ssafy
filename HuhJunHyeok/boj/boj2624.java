import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2624. 동전 바꿔주기
 */
public class boj2624 {
    static int T, K; // T: 목표 금액, K: 동전의 가지 수
//    static int[][] coins, dp; // dp[n][k]: n원을 k개 이내의 동전으로 교환하는 경우의 수, 2차원 dp
    static int[][] coins;
    static int[]  dp; // 1차원 dp
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        coins = new int[K + 1][2];
//        dp = new int[T + 1][K + 1];
        dp = new int[T + 1];

        for(int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            coins[i][0] = Integer.parseInt(st.nextToken()); // unit 동전의 단위 금액
            coins[i][1] = Integer.parseInt(st.nextToken()); // count 동전의 개수
        }

        /*
        2차원 dp
        dp[n][k] = dp[n][k] + dp[n - (unit * count)][k - 1] // unit: 사용 동전의 단위 금액, count: 사용 동전의 개수
        // n원을 k개까지 동전으로 교환하는 경우의 수 + ( n - (unit * count) )원을 이전 동전 개수(k -1)까지 교환하는 경우의 수
         */

        dp[0] = 1; // 0원을 만드는 경우는 안 내는 경우 1개. 1차원 dp용이라 이 위치.
        for(int k = 1; k <= K; k++) { // 어떤 단위 금액의 동전을 사용할지
            int unit = coins[k][0];
            int count = coins[k][1];

//            // 2차원 dp 풀이
//            dp[0][k - 1] = 1; // 0원을 만드는 경우는 안 내는 경우 1개.
//            for(int i = 1; i <= count; i++) { // 동전을 몇 개 사용할지
//                for(int j = unit * i; j <= T; j++) {
//                    dp[j][k] += dp[j - (unit * i)][k - 1];
//                }
//            }
//
//            for(int i = 1; i <= T; i++) { // 최종 값 계산을 위해 이전 동전 개수로 만들 수 있는 경우의 수를 현재 동전 개수로 만들 수 있는 경우의 수에 더함.
//                dp[i][k] += dp[i][k - 1];
//            }

            // 1차원 dp 풀이
            for(int i = T; i > 0; i--) { // 금액 범위
                for(int j = 1; j <= count; j++) { // 동전을 몇 개 사용하는지
                    int dpIdx = i - unit * j;
                    if(dpIdx < 0) {
                        break;
                    }
                    dp[i] += dp[dpIdx];
                }
            }
        }

//        System.out.println(dp[T][K]); // 2차원 dp 결과 출력
        System.out.println(dp[T]); // 1차원 dp 결과 출력
    }
}
