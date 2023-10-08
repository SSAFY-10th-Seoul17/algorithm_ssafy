import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj18427 {
    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        int[] dp = new int[h + 1];
        dp[0] = 1;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int top = 0;
            while (st.hasMoreTokens()) {
                arr[top++] = Integer.parseInt(st.nextToken());
            }

            for (int i = h; i > 0; i--) {
                for (int j = 0; j < top; j++) {
                    if (arr[j] > i) {
                        break;
                    }

                    dp[i] += dp[i - arr[j]];
                    dp[i] %= MOD;
                }
            }
        }

        System.out.println(dp[h]);
    }

}

