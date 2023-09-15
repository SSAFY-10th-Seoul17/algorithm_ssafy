package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호텔 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 고객 수 c
		int c = Integer.parseInt(st.nextToken());
		// 도시의 개수 n
		int n = Integer.parseInt(st.nextToken());
	
		// 고객 수 i명일 경우 치룰 최소 비용
		int[] dp = new int[c + 100]; 
		Arrays.fill(dp, 100000);
		dp[0] = 0;
		// 적어도 c명, 투자해야 하는 돈의 최솟값
		// 비용, 고객수
		int[][] arr = new int [n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 비용
			arr[i][0] = Integer.parseInt(st.nextToken());
			// 고객 수
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < dp.length; i++) {
			for(int j = 0; j < n; j++) {
				if(i - arr[j][1] >= 0) {
					dp[i] = Math.min(dp[i], dp[i - arr[j][1]] + arr[j][0]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = c; i < dp.length; i++) {
			if(result > dp[i]) result = dp[i];
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(result);

	
	}// end of main
	
}// end of class
