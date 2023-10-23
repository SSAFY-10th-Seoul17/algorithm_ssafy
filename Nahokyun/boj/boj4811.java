package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj4811 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int[] arr=new int[1001];
        int max=0;
        int idx=1;

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n==0) {
                break;
            }
            arr[idx++]=n;
            max=Math.max(max, n);
        }

        long[] dp=new long[max+1];
        dp[0]=1;
        for(int i=1;i<=max;i++) {
            for(int j=0;j<i;j++) {
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }

        for(int i=1;i<idx;i++) {
            sb.append(dp[arr[i]]).append('\n');
        }

        System.out.println(sb);

    }


}

