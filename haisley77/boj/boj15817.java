import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][X+1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine()," ");
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            dp[i][0] = 1;
            for (int j = 1; j <= X; j++){
                for (int cnt = 0; j - cnt*L >= 0 && cnt <= C; cnt++){
                    dp[i][j] += dp[i-1][j-cnt*L];
                }
            }
        }
        System.out.println(dp[N][X]);
    }
}
