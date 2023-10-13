package toyproject.somedaybucket.myAlgo.boj;

import java.util.*;

public class boj8394 {

    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new int[10000001];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
            dp[i]%=10;
        }
        System.out.println(dp[n]);


    }
}

