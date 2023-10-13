package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//18243번
public class SmallWorldNetwork {
	
		private static int[][] connect;
		private static int n;
		private static int k;
		private static final int INF = Integer.MAX_VALUE;
		private static int maxDist = 0;
		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// 사람의 수 n
			n = Integer.parseInt(st.nextToken());
			// 친구 관계의 수 k
			k = Integer.parseInt(st.nextToken());
			// 연결 단계
			connect = new int[2*k][2];
			
			for(int i = 0; i < 2*k; i+=2){
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				connect[i] = new int[]{a, b};
				connect[i+1] = new int[] {b, a};
			}
			
			FloydWarshall();
			
			if(maxDist <= 6) System.out.println("Small World!");
			else System.out.println("Big World!");
			
			
		}// end of main

		private static void FloydWarshall() { // 플로이드 워셜 알고리즘
			int[][] dist = new int[n+1][n+1];
			
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i!=j) {
						dist[i][j] = INF;
					}
				}
			}
			
			for(int i = 0; i < 2*k; i++) {
				dist[connect[i][0]][connect[i][1]] = 1;
			}
			
			for(int l = 1; l <= n; l++) {
				for(int i = 1; i <= n; i++) {
					for(int j = 1; j <= n; j++) {
						if(dist[i][l] != INF && dist[l][j] != INF) {
							dist[i][j] = Math.min(dist[i][j], dist[i][l] + dist[l][j]);
						}
					}
				}
				
			}
			
			for(int i = 1; i <= n; i++) {
				for(int j = i+1; j <= n; j++) {
					if(maxDist < dist[i][j]) maxDist = dist[i][j];
				}
			}
			
		} // end of FloydWarshall
		
}// end of class
