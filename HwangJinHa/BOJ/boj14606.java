import java.util.Scanner;

public class boj14606 {
	static int n;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new int[n + 1];
		
		dp[1] = 0;
		for(int i = 2; i < n + 1; i++) {
			int a = i / 2;
			int b = i / 2 + i % 2;
			dp[i] = dp[i/2] + dp[i/2+1] + (i/2);
			dp[i] = dp[a] + dp[b] + a * b;
		}
		System.out.println(dp[n]);
	}
}
