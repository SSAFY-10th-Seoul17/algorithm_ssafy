import java.util.*;
import java.io.*;
// 📝 

public class BOJ11058_크리보드 {
	static int n;
	static long[] dp;
	public static void main(String[] args) throws Exception {
		// 버튼 4개, 버퍼가 비어있지 않은 경우에 
		// n번 눌러서 출력된 a의 개수 최대
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new long[n+1];
		
		// a출력
		for (int i=0; i<=n; i++) {
			dp[i] = i;
		}
		
		for (int i=4; i<=n; i++) {
			long buffer = dp[i-3]; // 복사해놓은 값
			
			for (int j=i, index=2; j<=n; j++, index++) { // i-3을 가지고 계속 복사하는 상황
				dp[j] = Math.max(dp[j], dp[i-3]*index);
			}
		} 
		
		System.out.println(dp[n]);
	}
}
