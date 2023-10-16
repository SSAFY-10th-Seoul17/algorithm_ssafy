import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj11058 {
	static int res;
	static int temp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];
		// dp 배열 초기화
		for (int i = 1; i <= Math.min(N, 6); i++) {
			dp[i] = i;
		}
		
		for (int i = 7; i <= N; i++) {
			dp[i] = Math.max(dp[i-3]*2, dp[i]);
			dp[i] = Math.max(dp[i-4]*3, dp[i]);
			dp[i] = Math.max(dp[i-5]*4, dp[i]);
			dp[i] = Math.max(dp[i-6]*5, dp[i]);
		}
		System.out.println(dp[N]);
	}

}
