import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2624 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		int[] dp = new int[T + 1];
		dp[0] = 1;
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			for (int j = T; j >= 1; j--) {
				for (int l = 1; l <= n; l++) {
					if (p * l > j) {
						break;
					}
					dp[j] += dp[j - p * l];
				}
			}
		}

		System.out.println(dp[T]);
	}
}
