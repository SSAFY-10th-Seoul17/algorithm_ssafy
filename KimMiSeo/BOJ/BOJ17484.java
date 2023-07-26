

import java.util.*;
import java.io.*;

public class BOJ17484 {
	static int n,m;
	static int graph[][];
	static int min = Integer.MAX_VALUE;
	
	static int[] dy = {-1, 0, 1};
	static int[] visited;
	
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
		
		for (int i=0; i<m; i++) {
			visited = new int[n];
			visited[0] = i;
			dfs(1,i,-1);
		}
		
		System.out.println(min);
		
		
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
