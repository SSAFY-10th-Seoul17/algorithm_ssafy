package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj3066 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int test = 1; test <= t; test++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int[] dp = new int[n+1];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }


            int idx=1;
            dp[0]=arr[0];
            for(int i=1;i<n;i++) {
                int cur=arr[i];

                if(dp[idx-1]<arr[i]) {
                    dp[idx++]=arr[i];
                }else {
                    int left=0;
                    int right=idx;
                    while(left<=right) {
                        int mid=(left+right)>>1;
                        if(dp[mid]>cur) {
                            right=mid-1;
                        }else {
                            left=mid+1;
                        }
                    }
                    dp[left]=cur;
                }
            }

            System.out.println(idx);
        }
    }

}
