package BOJ;

import java.util.*;
import java.io.*;

public class boj09084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int[] coins = new int[N+1];
            for (int i = 1; i < N+1; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[][] memo = new int[N + 1][M + 1];

            for (int i = 1; i < N + 1; i++) {
                memo[i][0] = 1;
                for (int j = 1; j < M + 1; j++) {
                    if (j < coins[i]) {
                        memo[i][j] = memo[i - 1][j];
                        continue;
                    }
                    memo[i][j] = memo[i][j-coins[i]] + memo[i-1][j];
                }
            }
            System.out.println(memo[N][M]);
        }
    } // end of main
} // end of class