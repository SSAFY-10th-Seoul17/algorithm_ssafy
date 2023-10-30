import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 27114. 조교의 맹연습
 */
public class boj27114 {
    /**
     * A: 좌로 돌아 소모 에너지
     * B: 우로 돌아 소모 에너지
     * C: 뒤로 돌아 소모 에너지
     * K: 최종 소모 에너지 양
     * 1 이상 1,000,000 이하의 값.
     */
    static int A, B, C, K;
    /**
     * dp: dp 배열
     * turningCost: 한 바퀴 도는 경우 소모하는 에너지의 양
     * turningCnt: 한 바퀴 도는 경우 제식 수행 횟수
     * 한 바퀴 도는 경우는 아래와 같다(6가지)
     * 좌우 A+B
     * 뒤뒤 2C
     * 좌좌뒤 2A+C
     * 우우뒤 2B+C
     * 좌좌좌좌 4A
     * 우우우우 4B
     */
    static int[] dp, turningCost, turningCnt = {2, 2, 3, 3, 4, 4};
    static final int MAX = Integer.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        dp = new int[K + 1];
        for(int i = 1; i <= K; i++) {
            dp[i] = MAX;
        }

        turningCost = new int[] {A + B, C << 1, (A << 1) + C, (B << 1) + C, A << 2, B << 2};
        for(int i = 0; i < 6; i++) {
            int cost = turningCost[i];
            if(cost <= K) {
                dp[cost] = Math.min(dp[cost], turningCnt[i]);
            }
        }

        for(int i = 1; i <= K; i++) {
            for(int j = 0; j < 6; j++) {
                int prevDpIdx = i - turningCost[j];
                if(prevDpIdx >= 0 && dp[prevDpIdx] != MAX) {
                    dp[i] = Math.min(dp[i], dp[prevDpIdx] + turningCnt[j]);
                }
            }
        }


        System.out.println(dp[K] == MAX ? -1 : dp[K]);
    }
}
