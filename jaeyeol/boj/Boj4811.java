import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj4811 {
    static long[][] dp = new long[31][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        dp[0][0] = 1;

        String read;
        while (!(read = br.readLine()).equals("0")) {
            int n = Integer.parseInt(read);
            result.append(getStrCount(n, 0)).append("\n");
        }

        System.out.print(result);
    }

    private static long getStrCount(int n, int halfDrugs) {
        if (dp[n][halfDrugs] > 0) {
            return dp[n][halfDrugs];
        }

        long count = 0;
        if (n > 0) {
            count += getStrCount(n - 1, halfDrugs + 1);
        }
        if (halfDrugs > 0) {
            count += getStrCount(n, halfDrugs - 1);
        }

        return dp[n][halfDrugs] = count;
    }

}

