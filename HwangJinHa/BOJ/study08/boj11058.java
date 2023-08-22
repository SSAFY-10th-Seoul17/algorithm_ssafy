import java.io.*;
import java.util.*;

// 크리보드
public class boj11058 {
	static int n;
	static long[] dp;
	static int result;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(br.readLine());
		dp = new long[Math.max(n+1, 3)];
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i-1] + 1;
			idx = 2;
			for(int j = i - 3; j >= 0; j--) {
				dp[i] = Math.max(dp[i], dp[j] * idx++);
			}
		}
		System.out.println(dp[n]);
		
	} // end of main
}
