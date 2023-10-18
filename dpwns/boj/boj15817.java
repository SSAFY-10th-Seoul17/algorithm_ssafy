import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
        int[][] pipes = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 2; j++) {
                pipes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[x+1];
        dp[0] = 1;
        
        for (int i = 0; i < n; i++) {
            int len = pipes[i][0], cnt = pipes[i][1];
            for (int j = x; j >= len ; j--) {
                for (int k = 1; k <= cnt && j-len*k >= 0; k++) {
                    dp[j] += dp[j-len*k];
                }
            }
        }
        System.out.println(dp[x]);
        br.close();
    }
}
