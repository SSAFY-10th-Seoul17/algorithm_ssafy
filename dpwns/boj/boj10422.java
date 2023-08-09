import java.util.*;
import java.io.*;

class Main {
    public static final int MOD = 1000000007;
    public static long[] dp = new long[5001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[0] = 1;
        dp[2] = 1;
        int t = Integer.parseInt(br.readLine());
        for(int T=0; T<t; T++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(recurr(n));
        }
        br.close();
    }

    public static long recurr(int n) {
        if(n % 2 == 0 && dp[n] == 0) {
            for(int i=0; i<n; i+=2) {
                /*
                    괄호 쌍을 나누려면 괄호를 이루는 2개 단위로 나누어 판단 -> 경우의 수 곱 연산
                    n=6이면 4개, 0개일때 2개를 집어넣는 경우 +
                    2개, 2개일때 2개를 집어넣는 경우 +
                    0개, 4개일 때 2개를 집어넣는 경우
                */
                dp[n] += recurr(n-i-2)*recurr(i);
                dp[n] %= MOD;
            }
        }
        return dp[n];
    }

}