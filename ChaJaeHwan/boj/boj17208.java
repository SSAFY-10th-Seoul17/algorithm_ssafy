import java.io.*;
import java.util.*;

public class boj17208 {

    static int N, M, K;
    static int[][][] dp = new int[101][301][301];
    static int[][] order;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        order = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            order[i] = new int[]{x, y};
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 1; k <= K; k++) {
                    if (j >= order[i][0] && k >= order[i][1]) {
                        // 처리를 안함 경우 VS 처리를 한 경우
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i - 1][j-order[i][0]][k-order[i][1]]+1);
//                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i - 1][M-order[i][0]][K-order[i][1]]+1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
//            for (int j = 1; j <= M; j++) {
//                for (int k = 1; k <= K; k++) {
//                    sb.append(dp[i][j][k]);
//                }
//                sb.append("\n");
//            }
//            sb.append("+++++++++++++++++++++\n");
        }
//        System.out.println(sb);
        System.out.println(dp[N][M][K]);

    }


}
