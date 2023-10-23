import java.io.*;

public class boj14606 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[11];
		dp[2] = 1;
		for(int i = 3; i <= n; i++) {
			int n1 = i / 2;
			int n2 = i % 2 != 0 ? i / 2 + 1 : i / 2;
			dp[i] = n1 * n2 + dp[n1] + dp[n2];
		}
		System.out.println(dp[n]);
	}
}