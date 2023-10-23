package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj12014 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= t; test++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[n];
            dp[0] = arr[0];
            int idx = 1;
            for (int i = 1; i < n; i++) {
                if (dp[idx - 1] < arr[i]) {
                    dp[idx++] = arr[i];
                    continue;
                }

                int left = 0;
                int right = idx;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (dp[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                dp[left] = arr[i];

            }


            sb.append("Case #").append(test).append('\n').append(idx >= m ? 1 : 0).append('\n');
        }
        System.out.println(sb);

    }

}
