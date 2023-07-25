import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 14606. 피자(Small)
 */
public class boj14606 {
	public static void main(String[] args) throws Exception {
		// 피자탑의 높이 => 즐거움 총합의 최댓값
		// 1 => 0
		// 2 => 1
		// 3 => 3
		// 4 => 6
		// 5 => 10
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		
		// 풀이 1
		// 높이가 n일 때, 즐거움 총합의 최댓값은 n(n-1)/2
		System.out.println(n * (n - 1) / 2);
		
		// 풀이 2
		// 높이가 n일 때의 최댓값을 f(n)이라 하면, f(n) = f(n-1) + (n-1), f(1) = 0
//		int[] dp = new int[11];
//		dp[1] = 0;
//		dp[2] = 1;
//		for(int i = 3; i <= n; i++) {
//			dp[i] = dp[i - 1] + (i - 1);
//		}
//		System.out.println(dp[n]);
		
		// 풀이 3 (재귀)
//		System.out.println(recursiveMethod(n));
	}
	
//	public static int recursiveMethod(int n) {
//		if(n == 1) {
//			return 0;
//		}
//		return recursiveMethod(n - 1) + (n - 1);
//	}
}
