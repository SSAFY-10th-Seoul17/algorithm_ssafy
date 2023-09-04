import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj9084 {
	private static int[][] dp;
	private static int[] coin;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			coin = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int i = 1; i <= N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			dp = new int[N+1][M+1];
			for (int i = 1; i <= N; i++) {
				dp[i][0] = 1;
				for (int j = 1; j <= M; j++) {
					dp[i][j] = dp[i-1][j];
					if (j - coin[i] < 0) continue;
					dp[i][j] += dp[i][j-coin[i]];		
				}
			}
			sb.append(dp[N][M]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
