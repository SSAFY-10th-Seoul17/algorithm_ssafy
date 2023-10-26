package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//10159번
public class 저울 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] dist = new int[n+1][n+1];

		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			dist[from][to] = 1;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(dist[i][k]==1 && dist[k][j]==1) {
						dist[i][j] = 1;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			int count = 0;
			for(int j = 1; j <= n; j++) {
				if(i==j) continue;
				if(dist[i][j] == 0 && dist[j][i] == 0) count++;
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
		
	}
}
