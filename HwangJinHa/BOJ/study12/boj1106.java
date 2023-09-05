import java.io.*;
import java.util.*;

// 호텔 
public class Main {
	static int[] dp;
	static int c, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		c = Integer.valueOf(st.nextToken());
		n = Integer.valueOf(st.nextToken());
		dp = new int[c + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		for (int city = 0; city < n; city++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cost = Integer.valueOf(st.nextToken());
			int effect = Integer.valueOf(st.nextToken());
			
			int i;
			int until = Math.min(effect, c);
			for (i = 1; i <= until; i++) 
				dp[i] = Math.min(dp[i], cost);
			for (; i <= c; i++) 
				dp[i] = Math.min(dp[i], dp[i-effect] + cost);
		}
		System.out.println(dp[c]);
	} // enf of main
}
