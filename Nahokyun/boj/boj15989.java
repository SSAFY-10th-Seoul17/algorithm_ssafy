package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj15989 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());

        int[] arr=new int[t];
        int max=0;
        for(int i=0;i<t;i++) {
            arr[i]=Integer.parseInt(br.readLine());
            max=Math.max(max, arr[i]);
        }
        int[] dp=new int[max+1];
        dp[1]=1;
        int mod=0;
        int num=0;
        int cur=0;
        for(int i=2;i<=max;i++) {
            num=i/6;
            mod=i%6;
            if(mod==1) {
                cur=num;
            }else {
                cur=num+1;
            }

            dp[i]=dp[i-1]+cur;
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<t;i++) {
            sb.append(dp[arr[i]]).append('\n');
        }
        System.out.println(sb);

    }

}
