import java.io.*;
import java.util.*;
/**
 * dp[0] = 1 로 초기화하여 동전 1개를 1번만 사용할 때에도 1가지가 포함될 수 있도록 하였습니다.
 */
public class BOJ9084_동전 {
    static int N,coins[],M;
    public static void main(String[] args) throws Exception{
        // 동전 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=1; i<=N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine()); // 만들어야 할 금액
            int[] dp = new int[M+1];
            dp[0] = 1;

            for (int i=1; i<=N; i++){
                for (int j=1; j<=M; j++){
                    if ( j - coins[i] >= 0 ){
                        dp[j] += dp[j-coins[i]];
                    }
                }
            }
            System.out.println(dp[M]);
        } // end of tc
    }
}
