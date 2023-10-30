import java.util.*;
import java.io.*;

public class Main {
    public static final int INF = 1000 * 1000 + 1;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] rgb = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[2][3];
        int min = Integer.MAX_VALUE;
        for (int st = 0; st < 3; st++) {    // 시작 선택
            for (int i = 0; i < 3; i++) {
                if(st == i) dp[0][i] = rgb[0][i];
                else dp[0][i] = INF;
            }

            for (int i = 1; i < n; i++) {   // 2번부터 n번 최소값 찾기
                for (int j = 0; j < 3; j++) {
                    dp[1][j] = Math.min(dp[0][(j + 4) % 3], dp[0][(j + 2) % 3]) + rgb[i][j];
                }
                dp[0] = Arrays.copyOf(dp[1], 3);
            }
            
            for (int i = 0; i < 3; i++) {   // 마지막 선택이 처음과 다른 경우
                if(i == st) continue;
                min = Math.min(dp[0][i], min);
            }
        }
        System.out.println(min);
        br.close();
    }

}
