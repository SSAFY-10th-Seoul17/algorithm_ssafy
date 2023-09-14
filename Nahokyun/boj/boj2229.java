package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n+1];
        int[] dp=new int[n+1];
        StringTokenizer st=new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        //dp[i]는 i를 포함하는 최적값
        for(int i=1;i<=n;i++){
            for(int j=1;j<i;j++){
                int max=Integer.MIN_VALUE;
                int min=Integer.MAX_VALUE;

                for(int k=j;k<=i;k++){
                    max=Math.max(max,arr[k]);
                    min=Math.min(min,arr[k]);
                }

                dp[i]=Math.max(dp[i],dp[j-1]+max-min);
            }
        }


        System.out.println(dp[n]);

    }
}
