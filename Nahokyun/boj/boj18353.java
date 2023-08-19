package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj18353 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];//해당 인덱스를 포함하는 가장 긴 수열의 길이
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }//입력 종료
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int lower = 0;
            for (int j = 1; j < i; j++) {
                if (arr[i] < arr[j]) {
                    if (lower < dp[j])
                        lower = dp[j];
                }
            }
            dp[i] = lower + 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(n - max);

    }

}
