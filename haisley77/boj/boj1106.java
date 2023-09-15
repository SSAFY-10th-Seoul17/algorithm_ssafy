import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1106 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] dp = new long[C+101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int cost = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());	
			for (int j = people; j <= C+100; j++) {
				dp[j] = Math.min(dp[j], dp[j-people] + cost);
			}
		}
		long min = dp[C];
		for (int i = C+1; i <= C+100; i++) {
			if (min > dp[i]) min = dp[i];
		}
		System.out.println(min);
	}

}
