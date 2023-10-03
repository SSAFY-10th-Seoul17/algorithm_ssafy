import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj27114 {
    static final int MAX = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(getMinCount(a, b, c, k));
    }

    private static int getMinCount(int a, int b, int c, int k) {
        int[][] dp = new int[k + 1][4];
        Arrays.fill(dp[0], MAX);
        dp[0][0] = 0;

        for (int i = 1; i <= k; i++) {

            for (int dir = 0; dir < 4; dir++) {
                dp[i][dir] = MAX;

                if (i >= a) {
                    dp[i][dir] = Math.min(dp[i][dir], dp[i - a][(dir + 3) % 4] + 1);
                }

                if (i >= b) {
                    dp[i][dir] = Math.min(dp[i][dir], dp[i - b][(dir + 1) % 4] + 1);
                }

                if (i >= c) {
                    dp[i][dir] = Math.min(dp[i][dir], dp[i - c][(dir + 2) % 4] + 1);
                }
            }
        }

        return dp[k][0] >= MAX ? -1 : dp[k][0];
    }

}

