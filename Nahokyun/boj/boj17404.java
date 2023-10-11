package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17404 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] colors = new int[n][3];
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                colors[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //입력 종료


        int result=10000000;
        for(int c=0;c<3;c++) {
            for(int i=0;i<3;i++){
                dp[0][i]=i==c?colors[0][i]:10000;
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = (Math.min(dp[i - 1][1], dp[i - 1][2])) + colors[i][0];
                dp[i][1] = (Math.min(dp[i - 1][0], dp[i - 1][2])) + colors[i][1];
                dp[i][2] = (Math.min(dp[i - 1][0], dp[i - 1][1])) + colors[i][2];
            }

            for(int i = 0 ; i < 3; i++)
                if(i != c)
                    result = Math.min(result, dp[n-1][i]);
        }
        System.out.println(result);

    }
}
