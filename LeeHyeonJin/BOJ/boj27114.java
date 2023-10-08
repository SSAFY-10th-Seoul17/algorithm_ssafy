import java.io.*;
import java.util.*;

public class boj27114 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] moves = new int[3];
		for(int i=0; i<3; i++) {
			moves[i] = Integer.parseInt(st.nextToken());
		}
		int K = Integer.parseInt(st.nextToken());

		// solution
		int[][] dp = new int[K+1][4];
		for(int i=0; i<=K; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;

		int[] directions = { 3, 1, 2 };
		for(int i=0; i<=K; i++) {
			for(int j=0; j<4; j++) {
				if(dp[i][j] == Integer.MAX_VALUE) {
					continue;
				}
				for(int k=0; k<3; k++) {
					if(i+moves[k] > K) {
						continue;
					}
					dp[i+moves[k]][(j+directions[k])%4] = Math.min(dp[i][j]+1, dp[i+moves[k]][(j+directions[k])%4]);
				}
			}
		}

		// output
		System.out.println(dp[K][0] == Integer.MAX_VALUE ? -1 : dp[K][0]);
	}
}
