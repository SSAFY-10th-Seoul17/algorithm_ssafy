import java.io.*;
import java.util.*;

public class boj18353 {
	static int N;
	static int[] ap, dp;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		ap = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=N-1; i>=0; i--) ap[i] = Integer.parseInt(st.nextToken()); // 내림차순 처리

		// solution
		dp = new int[N];
		lis();
		int exceptions = N - max;

		// output
		System.out.println(exceptions);
	}

	static void lis() {
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(ap[j] < ap[i]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
			max = Math.max(max, dp[i]);
		}
	}
}
