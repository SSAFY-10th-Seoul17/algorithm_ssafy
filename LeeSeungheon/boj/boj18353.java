import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj18353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] map = new int[N];

        for (int num = 0; num < N; num++) {
            map[num] = Integer.parseInt(st.nextToken());
        }

        solve(map);
    }

    private static void solve(int[] map) {

        int[] dp = new int[map.length];

        for (int cur = 1; cur < map.length; cur++) {

            for (int pre = cur - 1; pre >= 0; pre--) {
                if (map[cur] < map[pre] && dp[cur] < dp[pre] + 1){
                    dp[cur] = dp[pre] + 1;
                }

            }
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(dp.length - Arrays.stream(dp).max().getAsInt() - 1);
    }
}
