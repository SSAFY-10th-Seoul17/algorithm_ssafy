import java.io.*;
import java.util.*;

public class boj17484 {
	static int[][][] dp;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j][0] = Integer.MAX_VALUE;
				dp[i][j][1] = Integer.MAX_VALUE;
				dp[i][j][2] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < m; i++) {
			dp[0][i][0] = map[0][i];
			dp[0][i][1] = map[0][i];
			dp[0][i][2] = map[0][i];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < m; j++) {
				dpMin(i, j, 0);
				dpMin(i, j, 1);
				dpMin(i, j, 2);
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < 3; j++) {
				result = Math.min(dp[n-1][i][j], result);
			}
		}
		System.out.println(result);
	}
	
	public static void dpMin(int i, int j, int dir) {
		try {
			if(dir == 0) {
				dp[i][j][0] = Math.min(dp[i][j][0], dp[i-1][j-1][2]);
			}
			else if(dir == 1) {
				dp[i][j][1] = Math.min(dp[i][j][1], dp[i-1][j+1][0]);
			}
			else {
				dp[i][j][2] = Math.min(dp[i][j][2], dp[i-1][j+1][0]);
			}
			} catch(Exception e) {
		}
		try {
			if(dir == 0) {
				dp[i][j][0] = Math.min(dp[i][j][0], dp[i-1][j][1]);
			}
			else if(dir == 1) {
				dp[i][j][1] = Math.min(dp[i][j][1], dp[i-1][j-1][2]);
			}
			else {
				dp[i][j][2] = Math.min(dp[i][j][2], dp[i-1][j][1]);
			}
			} catch (Exception e) {
		}
		if(dir == 0) {
			dp[i][j][0] += map[i][j];
		} else if(dir == 1) {
			dp[i][j][1] += map[i][j];			
		} else {
			dp[i][j][2] += map[i][j];
		}
	}
}