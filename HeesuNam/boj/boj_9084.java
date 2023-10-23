import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[] coins;
	private static int M;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				coins[j] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());

			sb.append(cnt()).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int cnt() {
		int[] dp = new int[M + 1];
		dp[0]=1;
		for (int i = 0; i < N; i++) {
			for (int j = coins[i]; j <= M; j++) {
				dp[j]+=dp[j-coins[i]];
			}
		}
		return dp[M];
	}
} // end of class
