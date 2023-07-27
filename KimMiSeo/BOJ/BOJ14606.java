

// ✅ 📝 
// 10:09 - 10:22
/*
 * dp로 풀었지만 정확한 풀이인지 모르겠습니다🥲 
 * */

import java.util.*;
import java.io.*;
public class BOJ14606 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		
		int sum = 0;
		int[] dp = new int[n+1];
		
		if (n>1) {
			dp[2] = 1;
		}
		
		
//		for (int i=3; i<=n; i++) {
//			dp[i] = i-1 + dp[i-1];
//		}
//		
//		System.out.println(dp[n]);
		solve(n);
		
		
		

	}
	
	private static int solve(int num) {
        int[] dp = new int[num + 1];
        dp[1] = 0; // dp[2] = 1  // dp[3] = dp[2] + dp[1] + 2

        for (int i = 2; i < dp.length; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
            	System.out.println(i+" "+j);
                max = Math.max(max, dp[j] + dp[i - j] + (j * (i - j)));
            }
            dp[i] = max;
        }
        return dp[num];
    }
}
