import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj18353 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] soldiers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			soldiers[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N]; // 가장 긴 감소하는 부분순열 길이를 담은 배열
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
		}

		int max = 1;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (soldiers[i] < soldiers[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}

		System.out.println(N - max); // 전체 병사의 수 - 가장 긴 감소하는 부분순열 길이
	}
}