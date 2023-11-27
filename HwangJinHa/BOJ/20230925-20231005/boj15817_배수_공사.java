package study10월1주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15817_배수_공사 {

    static int n, x;
    static int[] dp;
    static int[] dpNext;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dp = new int[x+1];
        dpNext = new int[x+1];
        dpNext[0] = 1;

        while (n-- > 0) {
            dp = Arrays.copyOf(dpNext, x+1);
            st = new StringTokenizer(br.readLine(), " ");
            int len = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            for (int pick = 1; pick <= amount; pick++) {
                int lenNow = len * pick;
                for (int i = x - lenNow; i >= 0; i--) {
                    dpNext[i + lenNow] += dp[i];
                }
            }
        }
        System.out.println(dpNext[x]);

    }
}