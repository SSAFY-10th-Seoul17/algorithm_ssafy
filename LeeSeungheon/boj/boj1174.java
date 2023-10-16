import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1174 {

    static int N;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if(N <= 10){
            System.out.println(N-1);
            return;
        }
        solve();
        System.out.println(result);
    }

    private static void solve() {

        int[][] dp = new int[11][11];
        // 9876543210
        Arrays.fill(dp[0], 1);
        dp[0][10] = 10;

        int maxRow = setDP(dp);

        if (maxRow == -1) {
            result = -1;
            return ;
        }

        for (int row = maxRow - 1; row >= 0; row--) {
            int sum = 0;
            for (int column = row; column < 10; column++) {
                sum += dp[row][column];

                if (sum >= N) {
                    N -= sum - dp[row][column];
                    result += ((column) * Math.pow(10, row));
                    break;
                }
            }
        }

    }

    private static int setDP(int[][] dp) {

        for (int row = 1; row <= 10; row++) {
            dp[row][10] += dp[row - 1][10];
            for (int column = row; column < 10; column++) {

                dp[row][column] = dp[row][column - 1] + dp[row - 1][column - 1];
                dp[row][10] += dp[row][column];

                if (dp[row][10] >= N) {
                    N -= dp[row][10] - dp[row][column];
                    result += ((column) * Math.pow(10, row));
                    return row;
                }
            }
        }
        return -1;
    }
}