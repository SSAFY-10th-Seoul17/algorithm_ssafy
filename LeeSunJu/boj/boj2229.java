import java.io.*;
import java.util.*;

public class boj2229 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			int max = arr[i - 1];
			int min = arr[i - 1];
			for (int j = 1; i - j >= 0; j++) {
				max = Math.max(max, arr[i - j]);
				min = Math.min(min, arr[i - j]);
				dp[i] = Math.max(dp[i], dp[i - j] + max - min);
			}
		}

		System.out.println(dp[N]);
	}
}
