import java.io.*;
import java.util.*;

public class boj2624 {
	static int T, k;
	static Coin[] coins;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		T = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		coins = new Coin[k];
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coins[i] = new Coin(
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken())
			);
		}

		// solution
		dp = new int[T+1]; // dp[i] : i원을 만드는 경우의 수
		dp[0] = 1; // 0원을 만드는 경우의 수 = 1가지
		for(int type = 0; type < k; type++) dp(type);

		// output
		System.out.println(dp[T]);
	}

	static void dp(int type) {
		for(int price = T - coins[type].value; price >= 0; price--) { // 남은금액을 채울 때까지
			if(dp[price] == 0) continue; // 해당 금액을 만드는 경우의 수가 0이라면, 고려 X
			for(int cnt = 1; cnt <= coins[type].cnt; cnt++) {
				int nextPrice = price + coins[type].value * cnt;
				if(nextPrice > T) break; // 목표금액을 초과하면 중단
				dp[nextPrice] += dp[price];
			}
		}
	}
}

class Coin {
	int value, cnt;

	public Coin(int value, int cnt) {
		this.value = value;
		this.cnt = cnt;
	}
}
