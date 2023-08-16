import java.io.*;
import java.util.*;

/* 복붙할 때 점화식 :
 *  - ctrl-V는 여러번 누를 수 있음 -> 결과적으로 복사하려면 버튼 3개 다 눌러야 하기 때문에 -> ctrl-V누른 횟수 + 2
 *  - 따라서 dp[현재인덱스 - (ctrl-V누른횟수 + 2)] == 현재 복붙하기 직전의 인덱스
 *  - 그리고 현재인덱스와 복붙 이전 인덱스의 문자열 길이의 차이는 (한번누른횟수+1)를 곱한만큼
 */
public class boj11058 {
	static int N;
	static long dp[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());

		// solution
		dp = new long[N+1];
		dp();

		// output
		System.out.println(dp[N]);
	}

	static void dp() {
		for(int i=1; i<=N; i++) {
			dp[i] = dp[i-1] + 1; // 1번 눌렀을 경우 -> 길이 += 1
			for(int j=1; j<=i-3; j++) { // 복붙하려면 최소한 직전의 인덱스보다는 3만큼 커야함
				dp[i] = Math.max(dp[i-(j+2)] * (j+1), dp[i]); // 2,3,4번 눌렀을 경우 -> (현재인덱스 - (v클릭횟수+2))길이 *= (v클릭횟수+1)
			}
		}
	}
}
