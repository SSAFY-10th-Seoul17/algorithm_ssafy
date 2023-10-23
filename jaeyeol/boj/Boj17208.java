import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17208 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(getMaxOrder(m, k, n, br));
    }

    private static int getMaxOrder(int m, int k, int n, BufferedReader br) throws IOException {
        int[][] dp = new int[m + 1][k + 1];

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x > m || y > k) {
                continue;
            }

            for (int i = m; i >= x; i--) {
                for (int j = k; j >= y; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - x][j - y] + 1);
                }
            }

        }

        return dp[m][k];
    }

}

