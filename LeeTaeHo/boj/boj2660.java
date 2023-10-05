import java.io.*;
import java.util.*;

public class boj2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 100000000);
            dp[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            if (p1 == -1 && p2 == -1) break;

            dp[p1][p2] = dp[p2][p1] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        int[] score = new int[n + 1];
        int minValue = 100000000;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (score[i] < dp[i][j]) {
                    score[i] = dp[i][j];
                }
            }
            if (minValue > score[i]) {
                minValue = score[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == minValue) {
                cnt++;
                list.add(i);
            }
        }

        sb.append(minValue).append(" ").append(cnt).append("\n");
        for (int i : list) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}