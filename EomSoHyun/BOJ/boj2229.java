import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		for (int i = 0; i < dp.length; i++) {
			if (i > 0) {
				dp[i] = dp[i-1];
			}
			for (int j = i-1; j >= 0; j--) {
				dp[i] = Math.max(dp[i], Math.abs(nums[i] - nums[j]) + (j > 0 ? dp[j-1] : 0));					
			}
		}
		
		System.out.println(dp[n-1]);
		
	}
}
