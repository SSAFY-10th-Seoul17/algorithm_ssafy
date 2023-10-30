package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj17208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Point[] arr = new Point[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[][][] dp = new int[n + 1][m + 1][k + 1];
        // [n번 인덱스까지 도달했을때][버거소모 m개][감튀소모 k개]

        for (int i = 1; i <= n; i++) {

            int curBurger = arr[i].x;
            int curFried = arr[i].y;

            for (int j = 0; j <=m ; j++) {
                for (int l = 0; l <= k; l++) {
                    if(j>=curBurger&&l>=curFried) {
                        dp[i][j][l] = Math.max(dp[i - 1][j - curBurger][l - curFried] + 1, dp[i - 1][j][l]);
                    }else{
                        dp[i][j][l]=dp[i-1][j][l];
                    }
                }
            }
        }

        System.out.println(dp[n][m][k]);

    }
}

