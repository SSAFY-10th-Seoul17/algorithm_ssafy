import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj18243 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i] ,7);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            dp[A][B] = 1;
            dp[B][A] = 1;
        }

        solve(dp);
    }

    private static void solve(int[][] dp) {

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if(i == j){
                    continue;
                }
                for (int k = 1; k < dp.length; k++) {
                    if(i == k || j == k){
                        continue;
                    }
                    dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = i+1; j < dp.length; j++) {
                if (dp[i][j] > 6) {
                    System.out.println("Big World!");
                    return;
                }
            }
        }

        System.out.println("Small World!");
    }
}
