import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] person = new int[N + 1];

        for (int num = 1; num <= N; num++) {
            person[num] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(person));
    }

    private static int solve(int[] person) {

        int[] dp = new int[person.length];

        for (int num = 1; num < person.length; num++) {
            int lastDiff = 0;
            dp[num] = dp[num - 1];

            for (int idx = 1; idx < num; idx++) {
                int diff2;
                if ((diff2 = Math.abs(person[num] - person[num - idx])) <= lastDiff) {
                    break;
                }
                lastDiff = diff2;
                dp[num] = Math.max(dp[num], dp[num - idx - 1] + lastDiff);
            }
        }
        return dp[person.length-1];
    }
}
