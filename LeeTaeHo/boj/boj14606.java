import java.io.*;

public class boj14606 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[11];
		dp[0] = dp[1] = 0;
		for(int i = 2; i <= 10; i++) {
			dp[i] = i-1 + dp[i-1];
		}
		System.out.println(dp[n]);
	}
}
