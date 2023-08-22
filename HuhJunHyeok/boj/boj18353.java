import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 18353. 병사 배치하기
 */
public class boj18353 {
    static int N, maxAscendingLength, answer;
    static int[] soldiers, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        soldiers = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = N - 1; i >= 0; i--) { // 전투력 거꾸로 입력받음.
            soldiers[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; // 1로 초기화. 증가하는 부분 수열의 길이를 의미하는데, 해당 칸도 포함하기 때문.
        }

        lisDP();
        findMaxAscendingLength();
        answer = N - maxAscendingLength; // 열외해야하는 병사의 수는 병사 전체 수 - 증가하는 부분 수열의 최대 길이

        System.out.println(answer);
    }

    /**
     * LIS 알고리즘을 적용하여 dp 배열 채우기.
     */
    public static void lisDP() {
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) { // i번째 앞의 모든 전투력 값들에 대해 확인.
                if(soldiers[j] < soldiers[i]) { // i번째 전투력 값에 대해 가장 긴 증가하는 부분 수열 길이 구하기.
                    dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1; // Math.max() 대신 삼항연산자 사용. Math.max()도 내부에선 삼항연산자로 동작.
                }
            }
        }
    }

    /**
     * dp 배열에서 증가하는 부분 수열 길이의 최댓값 구하기
     */
    public static void findMaxAscendingLength() {
        maxAscendingLength = dp[0];
        for(int i = 1; i < N; i++) {
            maxAscendingLength = maxAscendingLength > dp[i] ? maxAscendingLength : dp[i];
        }
    }
}
