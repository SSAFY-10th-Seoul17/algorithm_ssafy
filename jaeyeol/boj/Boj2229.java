import java.io.*;
import java.util.*;

public class Boj2229 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxTeamScore(arr, n));
    }

    private static int getMaxTeamScore(int[] arr, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int max = 0;
            int min = Integer.MAX_VALUE;

            for (int j = i; j > 0; j--) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);

                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

        return dp[n];
    }

}

