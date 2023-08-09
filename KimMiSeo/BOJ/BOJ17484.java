

import java.util.*;
import java.io.*;

public class BOJ17484 {
	static int n,m;
	static int graph[][];
	static int min = 100*n*m;
	
	static int[] dy = {-1, 0, 1};
	static int[] visited;
//	static int result = 100*n*m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		graph = new int[n][m];
		
		for (int i=0; i<n; i++) {
			temp = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				graph[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
//		for (int i=0; i<m; i++) {
//			visited = new int[n];
//			visited[0] = i;
//			dfs(1,i,-1);
//		}
//		
//		System.out.println(min);
		
		//--------- dp ----------		
		int result = solve(graph);
		System.out.println(result);
		
	}
	
	private static int solve(int[][] graph) {
		int[][][] dp = new int[n][m][3];
		
		// 첫 줄 초기화
		for (int i=0; i<m; i++) {
			dp[0][i][0] = dp[0][i][1] = dp[0][i][2] = graph[0][i];
		}
		
		// dp 초기화
		for (int i=1; i<n; i++) {
			for (int j=0; j<m; j++) {
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = 100*n*m;
			}
		}
		
		
		for (int i=1; i<n; i++) {
			for (int j=0; j<m; j++) {
				for(int k=0; k<3; k++) {
					// 이전 좌표 구하기
					int beforeX = i - 1;
					int beforeY = j - dy[k];
					
					// 이전좌표, 다른방향에서 온 값 + 현재값 vs 현재 dp값
					if (isValid(beforeX, beforeY)) {						
						dp[i][j][k] = Math.min(dp[i][j][k], dp[beforeX][beforeY][(k+1)%3]+graph[i][j]);
						dp[i][j][k] = Math.min(dp[i][j][k], dp[beforeX][beforeY][(k+2)%3]+graph[i][j]);
					}
				}
			}
		}
		
		int result = 100*n*m;
		for (int i=0; i<m; i++) {
			for (int j=0; j<3; j++) {
				result = Math.min(result, dp[n-1][i][j]);
			}
		}
		
		return result;
		
	}
	
	private static boolean isValid(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	public static void dfs(int i, int j, int dir) {
		
		if (i == n) {
			int sum = graph[0][visited[0]];
			for (int k=1; k<n; k++) {
				sum += graph[k][visited[k]];
			}
			
			min = Math.min(min, sum);	
			return;
		}
		
		
		for (int k=0; k<3; k++) {
			int ny = j + dy[k];
			
			if (ny>=0 && ny<m && dir!=k) {
				visited[i] = ny;
				dfs(i+1, ny, k);
			}
			
		}
		
	}
}
