package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj27114 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] cost = new int[3];
        int[] direction = {3, 1, 2};
        for (int i = 0; i < 3; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int k = Integer.parseInt(st.nextToken());

        //현재 필요한거  1.에너지 사용량 2.보고있는방향 3.현재 이동의 비용
        //dp값 = 이동횟수

        int[][] dp = new int[k + 1][4];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], 1000001);
        }
        //0=정면, 1=3시, 2=6시, 3=9시

        dp[0][0] = 0;
//        dp[cost[0]][3] = 1;
//        dp[cost[1]][1] = 1;
//        dp[cost[2]][2] = 1;
        //k가 a,b,c보다 작을수 있음

        for (int l = 0; l < k + 1; l++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {

                    if (l + cost[j] <= k)
                        dp[l + cost[j]][(i + direction[j]) % 4] = Math.min(dp[l + cost[j]][(i + direction[j]) % 4], dp[l][i] + 1);

                }
            }
        }

        System.out.println(dp[k][0] != 1000001 ? dp[k][0] : -1);
    }
}
