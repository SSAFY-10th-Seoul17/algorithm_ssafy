import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int c = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());

        int[][] hotel = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            hotel[i][0] = Integer.parseInt(st.nextToken());
            hotel[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100001];   // 비용 100 - 1명 C가 1000명

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = i / hotel[j][0];
                if(cnt > 0) {
                    dp[i] = Math.max(dp[i], dp[i - hotel[j][0]] + hotel[j][1]);
                }
                else dp[i] = Math.max(dp[i], dp[i-1]);
            }
            if(dp[i] >= c) {
                System.out.println(i);
                break;
            }
        }
        br.close();
    }

}
