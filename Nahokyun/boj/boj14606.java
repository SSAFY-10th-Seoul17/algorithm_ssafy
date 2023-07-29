package boj;

import java.util.Scanner;

import static java.lang.Math.max;

public class boj14606 {
    public static int sum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dfs(n);
        System.out.println(sum);
//          DP 접근
//        dp=new int[11];
//        dp[1]=0;
//        dp[2]=1;
//        for(int i=3;i<=n;i++){
//            for(int j=1;j<i;j++){
//                dp[i]=max(dp[i],dp[j]+dp[i-j]+j*(i-j));
//            }
//        System.out.println(dp[n]);
    }

    private static void dfs(int num) {
        if (num == 1)
            return;
        if (num % 2 == 0) {
            sum += num * num / 4;
            dfs(num / 2);
            dfs(num / 2);
        } else {
            sum += ((num - 1) * (num + 1) / 4);
            dfs((num - 1) / 2);
            dfs((num + 1) / 2);
        }
    }
}

