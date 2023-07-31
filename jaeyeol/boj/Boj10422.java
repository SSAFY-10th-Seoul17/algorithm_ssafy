import java.io.*;

public class Boj10422 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int max = 0;
        int[] testCases = new int[t];

        for (int i = 0; i < t; i++) {
            testCases[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, testCases[i]);
        }

        dp = new long[max + 1];
        dp[0] = 1;

        StringBuilder result = new StringBuilder();
        for (int testcase : testCases) {
            result.append(testcase % 2 == 0 ? getBracketCount(testcase) : 0).append("\n");
        }
        System.out.print(result);
    }

    static long[] dp;
    static final int MOD = 1_000_000_007;
    private static long getBracketCount(int n) {
        if (dp[n] > 0) {
            return dp[n];
        }

        long sum = getBracketCount(n - 2);
        for (int i = 2; i < n; i += 2) {
            sum += getBracketCount(i) * getBracketCount(n - i - 2);
            sum %= MOD;
        }

        return dp[n] = sum;
    }

}
