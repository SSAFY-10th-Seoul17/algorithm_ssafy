import java.io.*;
import java.util.*;

/**
 * 아이디어 : 1~N 까지 집을 색칠하는 경우의 수 중 최소비용 탐색 => 완탐하면 시간초과 => DP
 * - 추가조건 : 1st != Nnd => 1st 집의 색깔에 따라 반복하면서 Nnd 색을 칠할때, 1st 와 같은 경우 제외
 */
public class boj17404 {
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		int[][] dp = new int[N][3];
		int res = INF;
		for(int s=0; s<3; s++) { // 1st 집
			for(int i=0; i<3; i++) { // 1st 집 초기화
				if(i == s) dp[0][i] = cost[0][i];
				else dp[0][i] = INF;
			}
			for(int i=1; i<N; i++) { // 2nd ~ Nst 집 초기화
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
			}
			for(int i=0; i<3; i++) { // 최소비용 탐색
				if(i == s) continue; // 1st == Nst 인경우, 제외
				res = Math.min(res, dp[N-1][i]);
			}
		}

		// output
		System.out.println(res);
	}
}

