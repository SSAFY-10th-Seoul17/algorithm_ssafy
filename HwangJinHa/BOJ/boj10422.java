import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static final long lim = 1000000007L;
	static int n;
	static long[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		dp = new long[5001];
		dp[0] = 1;
		dp[2] = 1;
		
		for(int i = 4; i <= 5000; i+=2) {
			for (int j = 2; j <= i; j+=2) {
				dp[i] += dp[j - 2] * dp[i - j] % lim;
				dp[i] %= lim;
			}
		}
		
		System.out.println(Arrays.toString(dp));
		int t = sc.nextInt();
		while (t-- > 0) {
			System.out.println(dp[sc.nextInt()]);
		}
	}
}