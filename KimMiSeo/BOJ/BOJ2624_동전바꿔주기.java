// 06:09 - 
//📝📝 

// 모든 경우를 다 탐색해야겠다는 생각으로 구현하려 했으나 구현하지 못했습니다.
// 검색을 해보고 구현해보았지만 명확한 이해가 되지 않은 상태입니다🥲 
import java.util.*;
import java.io.*;

public class BOJ2624_동전바꿔주기 {
	static int t,k;
	static int[][] coins;
	static boolean[] isSelected;
	static int[] dp;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		// k가지 동전, t원의 지폐 -> 동전
		// 교환 방법 가지수 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		coins = new int[k][2];
		dp = new int[t+1];
		for (int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coins[i][0] = Integer.parseInt(st.nextToken());
			coins[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// x 금액을 동전으로 교환하는 방법의 가짓수 
		dp[0] = 1;
		for (int i=0; i<k; i++) { // 동전 종류
			int coin = coins[i][0];
			for (int j=t; j>=coin; j--) { // t금액~ 동전 1개 금액
				for (int w=1; w<=coins[i][1]; w++) { // 1~동전 갯수
					if (j - (coin*w) < 0) { 
						break;
					}
					dp[j] += dp[j-(coin*w)];					
				}
			}
		}
		
		System.out.println(dp[t]);
		
	}
	
}
