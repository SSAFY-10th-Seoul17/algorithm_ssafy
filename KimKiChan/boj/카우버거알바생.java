package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//17208번
public class 카우버거알바생 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//주문의 수 N
		int N = Integer.parseInt(st.nextToken());
		// 남은 치즈버거의 개수 M
		int M = Integer.parseInt(st.nextToken());
		// 남은 감자튀김의 개수 K
		int K = Integer.parseInt(st.nextToken());
		
		int[][] order = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝
		int[][] dp = new int[M+1][K+1]; // dp[i][j] : 치즈버거 i개, 감자튀김 j개의 최대주문개수
		for(int i = 0; i <= M; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 0;
		int maxOrder = 0;
		
		for(int n = 0; n < N; n++) {
			for(int i = M; i>= order[n][0]; i--) {
				for(int j = K; j >= order[n][1]; j--) {
					if(dp[i-order[n][0]][j-order[n][1]]>=0) {
						dp[i][j] = Math.max(dp[i][j], dp[i-order[n][0]][j-order[n][1]]+1);
						maxOrder = Math.max(maxOrder, dp[i][j]);
					}
				}
			}
		}
		
		System.out.println(maxOrder);
		
	}// end of main
}// end of class
