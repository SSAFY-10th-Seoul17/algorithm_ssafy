import java.io.*;
import java.util.*;

// 동전 바꿔주기
public class boj2624 {
	static int t, c;
	static ArrayList<int[]> coins;
	
	static int[] dp;
	static int[] dpNext;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); 
		
		t = Integer.valueOf(br.readLine());
		dp = new int[t+1];
		dpNext = new int[t+1];
		dp[0] = 1;
		dpNext[0] = 1;

		c = Integer.valueOf(br.readLine());
		coins = new ArrayList<>();

		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			
			coins.add(new int[] {a, b});
		}
		
		Collections.sort(coins, (a, b) -> b[0]-a[0]);
		for (int[] coin : coins) {
			int value = coin[0];
			int amount = coin[1];
			for(int i = 0; i <= t; i++) { // dp[i]에 접근
				if (dp[i] == 0) continue;
				for(int quantity = 1; quantity <= amount; quantity++) {
					idx = i + quantity * value;
					if (idx > t)
						break;
					dpNext[idx] += dp[i];
				}
			}
			dp = Arrays.copyOf(dpNext, t+1);
		}
		
		System.out.println(dp[t]);
	}
}
