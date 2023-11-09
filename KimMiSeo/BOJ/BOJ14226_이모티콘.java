import java.util.*;
import java.io.*;
public class BOJ14226_이모티콘 {
    static int S;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        dp = new int[S*2 + 1]; // 마지막에 빼주는 경우도 생각하기 // s+2 까지만 해서 틀렸었음!!
        dp[2] = 2;

        for(int i=3; i<=dp.length-1; i++){
            dp[i] = i;
            // i를 i-1까지 나눠주면서 나누어 떨어지면 -> 나누어준 값 + 몫 으로 비교하기
            // 다 구했으면 -> 현재 값 + 1 이 이전 값보다 작으면 갱신해주기
            for (int div=2; div<i; div++){
                if (i % div == 0){ // 나누어 떨어지면
                    dp[i] = Math.min(dp[i], dp[div] + i/div );
                }
            }

            // 뒤에까지 계속 보면서 갱신해주기
            for (int j=i; j>=2; j--){
                dp[j-1] = Math.min(dp[j-1], dp[j]+1);
            }
        }
        System.out.println(dp[S]);
    }
}
