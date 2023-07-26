package KimMiSeo;

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
		
		
		for (int i=3; i<=n; i++) {
			dp[i] = i-1 + dp[i-1];
		}
		
		System.out.println(dp[n]);

	}
}
