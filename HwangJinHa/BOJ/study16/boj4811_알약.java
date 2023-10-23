package study10월3주차월;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj4811_알약 {
    static int n = 0;
    static long[][] dp;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dp = new long[31][31];
        for (int i = 0; i < 31; i++) Arrays.fill(dp[i], -1L);
        dp[0][0] = 1L;
        makeDP();

        StringBuilder sb = new StringBuilder();
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            sb.append(dp[n][0]).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeDP() {
        solution(30, 0);
    }

    private static long solution(int w, int h) {
        if (w < 0 || h < 0)
            return 0;

        if (dp[w][h] != -1)
            return dp[w][h];

        return dp[w][h] = solution(w-1, h+1) + solution(w, h-1);
    }
}