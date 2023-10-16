import java.util.*;
import java.io.*;

/**
 * "같은 조에 속하게 된 학생들의 나이 차이가 많이 날 경우에는 부정적인 효과가 나타날 수도 있다." 라는 것이 조금 모호했다고 생각합니다.
 * dp 문제임은 직감했지만 ,, 쉽지 않았습니다,, 다른 이들의 아이디어를 참고해서 풀었습니다. 다음에 다시 혼자 힘으로 풀 수 있길...
 */

public class BOJ2229_조짜기 {
    static int N;
    static int[] scores, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        scores = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++){
            int max = 0;
            int min = Integer.MAX_VALUE;
            scores[i] = Integer.parseInt(st.nextToken());

            for (int j=i; j>0; j--){
                max = Math.max(max, scores[j]);
                min = Math.min(min, scores[j]);
                dp[i] = Math.max(dp[i], max-min+ dp[j-1]);
            }
        }
        System.out.println(dp[N]);
    }
}
