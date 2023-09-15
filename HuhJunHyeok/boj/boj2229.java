import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2229. 조 짜기
 */
public class boj2229 {
    /**
     * N: 학생의 수, max & min: 그룹 내에서 점수의 최댓값과 최솟값
     * 1 <= N <= 1000
     */
    static int N, max, min;
    /**
     * scores: 나이 기준 정렬된 학생들의 점수(점수는 0 이상 10_000 이하), dp: dp 배열
     */
    static int[] scores, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        /*
         * dp 이용 풀이
         * dp[k]의 값은
         * (k - 1)번째까지 그룹핑 + k번째 점수 혼자 그룹핑.
         * ...
         * 첫 번째 점수 혼자 그룹핑 + 이후 점수 ~ k번째 점수까지 그룹핑.
         *
         * 위 결과들 중의 최댓값.
         */
        scores = new int[N + 1]; // dp 풀이를 위해 0번 index 사용하지 않음.
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());

            max = 0;
            min = 10_000;

            for(int j = i; j > 0; j--) {
                max = Math.max(max, scores[j]);
                min = Math.min(min, scores[j]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

        System.out.println(dp[N]);
    }
}
