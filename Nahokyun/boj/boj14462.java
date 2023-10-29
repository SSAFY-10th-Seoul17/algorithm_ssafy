package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj14462 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr1 = new int[n + 1];
        int[] arr2 = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= 4) {
                    int max = 0;
                    for (int k = 1; k < j; k++) {
                        max = Math.max(max, dp[i - 1][k]);
                    }

                    dp[i][j] = max + 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
            }
        }
        int max = 0;
        for (int i = 0; i <= n; i++) {
            //System.out.println(Arrays.toString(dp[i]));
            max = Math.max(max, dp[n][i]);
        }

        System.out.println(max);
    }

}

