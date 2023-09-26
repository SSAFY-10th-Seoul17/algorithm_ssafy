import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 15817. 배수 공사
 */
public class boj15817 {
    /**
     * N: 파이프의 종류의 수, x: 만들고 싶은 합친 파이프의 길이
     * 1 ≤ N ≤ 100 이며, 1 ≤ x ≤ 10_000
     */
    static int N, x;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        dp = new int[x + 1];
        dp[0] = 1;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int pipeLength = Integer.parseInt(st.nextToken());
            int pipeCount = Integer.parseInt(st.nextToken());

            for(int j = x; j > 0; j--) {
                for(int k = 1; k <= pipeCount; k++) {
                    if(j - pipeLength * k >= 0) {
                        dp[j] += dp[j - pipeLength * k];
                    }
                }
            }
        }

        System.out.println(dp[x]);
    }
}
