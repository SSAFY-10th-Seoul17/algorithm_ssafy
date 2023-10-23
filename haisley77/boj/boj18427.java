import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj18427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][H+1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++){
            dp[i][0] = 1;
            st = new StringTokenizer(br.readLine()," ");
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (st.hasMoreTokens()){
                list.add(Integer.parseInt(st.nextToken()));
            }
            for (int j = 1; j <= H; j++){
                dp[i][j] = (dp[i][j] + dp[i-1][j]) % 10007;
                for (int block: list){
                    if (j - block >= 0){
                        dp[i][j] = (dp[i][j] + dp[i-1][j-block]) % 10007;
                    }
                }
            }
        }
        System.out.println(dp[N][H]);
    }
}