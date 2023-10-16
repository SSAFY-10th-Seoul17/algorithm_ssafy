import java.io.*;
import java.util.*;

public class boj9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 동전의 가지수
			int[] coins = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());
			int[][] dp = new int[N][M + 1];
			for (int i = 0; i < N; i++) {
				for (int j = 1; j <= M; j++) {
					if (j % coins[i] == 0) {
						dp[i][j] = 1;
					}
				}
			}

			for (int i = 1; i < N; i++) {
				for (int j = 1; j <= M; j++) {
					for (int k = 0; j >= coins[i] * k; k++) {
						dp[i][j] += dp[i - 1][j - coins[i] * k];
					}
				}
			}

			sb.append(dp[N - 1][M]).append("\n");
		}
		System.out.print(sb.toString());
	}
}
