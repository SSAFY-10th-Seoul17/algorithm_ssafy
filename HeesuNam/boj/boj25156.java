import java.io.*;

public class Main {
	static final int DIV = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] S = br.readLine().toCharArray();

		int[] dp = new int[] { 0, 0, 0, 0 };

		for (int i = n - 1; i >= 0; i--) {
			dp[3] = (2 * dp[3]) % DIV;

			switch (S[i]) {
			case 'K':
				dp[0] = (dp[0] + 1) % DIV;
				break;
			case 'C':
				dp[1] = (dp[1] + dp[0]) % DIV;
				break;
			case 'O':
				dp[2] = (dp[2] + dp[1]) % DIV;
				break;
			case 'R':
				dp[3] = (dp[3] + dp[2]) % DIV;
				break;
			}
		}
		System.out.println(dp[3] % DIV);
	}
}
