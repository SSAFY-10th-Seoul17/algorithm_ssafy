import java.util.*;
import java.io.*;

public class Main {
    public static final int DIVIDE = 10007;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
        int[][] blocks = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = 0;
            while(st.hasMoreTokens()) {
                blocks[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[h+1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = h; j > 0; j--) {
                for (int k = 0; k < m; k++) {
                    if(blocks[i][k] == 0 || j-blocks[i][k] < 0) break;
                    dp[j] += dp[j-blocks[i][k]];
                }
                dp[j] %= DIVIDE;
                if(i == n-1) break;
            }
        }
        System.out.println(dp[h]);
        br.close();
    }
}
