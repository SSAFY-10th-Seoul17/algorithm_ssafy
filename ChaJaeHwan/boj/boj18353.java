import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj18353 {

    static int N;
    static int[] soldier;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        soldier = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            soldier[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (soldier[j] > soldier[i])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
        int max = 0;
        for(int i = 1; i <= N;i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);
    }

}
