import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 크리보드 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[n+1];
		
		for(int i = 1; i <= n; i++) {
			dp[i] = dp[i-1] + 1; // A
			for(int j = 3; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] * (j-1)); // 복사 붙여넣기
			}
		}
		
		System.out.println(dp[n]);
	}// end of main
}// end of class
