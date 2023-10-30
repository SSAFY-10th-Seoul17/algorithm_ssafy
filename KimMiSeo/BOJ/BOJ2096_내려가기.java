import java.util.*;
import java.io.*;

/**
 * 2차원 dp로 풀었습니다.
 * 순차적으로 내려오면서 최소, 최대를 구해서 마지막에 0,1,2 인덱스에 존재했을 때에서도 최소 최대를 구해주었습니다.
 */

public class BOJ2096_내려가기 {
    static int N, minresult, maxresult;
    static int[][] map, maxdp, mindp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 3개 숫자 중 하나를 골라서 시작, 위치한 곳 수의 합
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][3];
        maxdp = new int[N+1][3];
        mindp = new int[N+1][3];

        maxresult = Integer.MIN_VALUE;
        minresult = Integer.MAX_VALUE;

        for (int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=1; i<=N; i++){
            // 0 - 01
            maxdp[i][0] = Math.max(maxdp[i-1][0] , maxdp[i-1][1]) + map[i][0];
            mindp[i][0] = Math.min(mindp[i-1][0] , mindp[i-1][1]) + map[i][0];

            // 1 - 012
            maxdp[i][1] = Math.max(maxdp[i-1][0] , Math.max(maxdp[i-1][1], maxdp[i-1][2])) + map[i][1];
            mindp[i][1] = Math.min(mindp[i-1][0] , Math.min(mindp[i-1][1], mindp[i-1][2])) + map[i][1];

            // 2 - 12
            maxdp[i][2] = Math.max(maxdp[i-1][1] , maxdp[i-1][2]) + map[i][2];
            mindp[i][2] = Math.min(mindp[i-1][1] , mindp[i-1][2]) + map[i][2];
        }

        for (int i=0; i<3; i++){
            maxresult = Math.max(maxresult, maxdp[N][i]);
            minresult = Math.min(minresult, mindp[N][i]);
        }

        System.out.println(maxresult+" "+minresult);
    }
}
