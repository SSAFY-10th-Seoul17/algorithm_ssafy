import java.io.*;
import java.util.*;
/**
 * 처음에는 재귀로 풀어서 시간초과가 났었습니다. 할 수 있는 가지치기를 모두 해봐도 시간 초과가 나서 dp로 다시 풀었습니다.
 * 마지막 값만 10007로 나눈 나머지를 출력하게 했더니 틀렸습니다,, 더할 때 오버플로우가 나서 그런걸까요?
 */
public class BOJ18427_함께블록쌓기 {
    static int N,M,H;
    static int[][] map,dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()){
                map[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][H+1];
        dp[0][0] = 1;

        for (int i=1; i<=N; i++){
            for (int j=0; j<=H; j++){

                int temp = 0;
                for (int k=0; k<M; k++){
                    int cur = map[i-1][k];
                    if ( cur != 0 ){ // 블록이 있을 때
                        if (cur <= j){
                            temp += dp[i-1][j - cur];
                            temp %= 10007;
                        }
                    }
                }
                dp[i][j] = temp + dp[i-1][j];
                dp[i][j] %= 10007;
            }
        }
        System.out.println(dp[N][H]);
    }
}
