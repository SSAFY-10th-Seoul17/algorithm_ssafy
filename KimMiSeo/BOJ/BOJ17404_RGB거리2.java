import java.io.*;
import java.util.*;

/**
 * 1번째 집 색을 지정해주고, 그에 따라 2번째 집의 경우를 모두 따져서 값을 넣어주었습니다.
 * 그 이후는 dp 배열을 돌려서 풀었습니다.
 */
public class BOJ17404_RGB거리2 {
    static int N, minresult;
    static int[][] values, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = new int[N][3];

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                values[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][3];
        minresult = Integer.MAX_VALUE;

        for (int i=0; i<3; i++){ //  r g b 각각
            init();
            // 2번 째 일때
            if (i == 0){ // r
                dp[1][0] = 100000; // 큰수를 넣어서 min 으로 탈락 될 수 있게 한다.
                dp[1][1] = dp[1][1] + dp[0][0];
                dp[1][2] = dp[1][2] + dp[0][0];
            }

            else if (i == 1){ // g
                dp[1][0] += dp[0][1];
                dp[1][1] = 100000;
                dp[1][2] += dp[0][1];
            }
            else  { // b
                dp[1][0] += dp[0][2];
                dp[1][1] += dp[0][2];
                dp[1][2] = 100000;
            }

            for (int j=2; j<N; j++){
                dp[j][0] = Math.min((dp[j][0] + dp[j-1][1]) , (dp[j][0] + dp[j-1][2]));
                dp[j][1] = Math.min((dp[j][1] + dp[j-1][0]) , (dp[j][1] + dp[j-1][2]));
                dp[j][2] = Math.min((dp[j][2] + dp[j-1][0]) , (dp[j][2] + dp[j-1][1]));
            }

            int min = Integer.MAX_VALUE;
            for (int j=0; j<3; j++){
                if (i == j){
                    continue;
                }
                min = Math.min(dp[N-1][j], min);
            }
            minresult = Math.min(minresult, min);
        }
        System.out.println(minresult);
    }
    private static void init(){
        for (int i=0; i<N; i++){
            for (int j=0; j<3; j++){
                dp[i][j] = values[i][j];
            }
        }
    }

}
