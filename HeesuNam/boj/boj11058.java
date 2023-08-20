import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N <= 6) {
			System.out.println(N);
		} else {
			long[] dp = new long[N + 1];

			for (int i = 1; i <= 6; i++) {
				dp[i] = i;
			} // 3개이상부터 3개 단위로 a(i-3),c(i-2),v(i-1)누름

			for (int i = 7; i <= N; i++) {
				dp[i] = dp[i - 1] + 1; // A출력
				for (int j = 3; j < i; j++) {
					dp[i] = Math.max(dp[i], dp[i - j] * (j - 1)); // acv다누르는경우, 두번 v누르는 경우, 세번 v누르는경우
				}
			}
			System.out.println(dp[N]);
		}
	} // end of main
} // end of class
