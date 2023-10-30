import java.io.*;
import java.util.*;

public class boj15989_123더하기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			int N = Integer.parseInt(br.readLine());

			// solution
			int[] dp = new int[N+1];
			dp[0] = 1;

			for(int i=1; i<=3; i++) { // 주어진 재료별로(1,2,3)
				for(int j=1; j<=N; j++) { // 1~N 까지의 목표치를
					if(j-i >= 0) dp[j] += dp[j-i]; // 이전값을 이용해가면서 갱신
				}
			}

			// output
			sb.append(dp[N]).append("\n");
		}

		System.out.println(sb.toString());
	}
}
