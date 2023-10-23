import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 도로포장 { // 언제 어느 도로를 포장할 것인가
	
	private static int n;
	private static boolean[] isVisited;
	private static int[][] arr;
	private static int k;
	private static int[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1]; // arr[i][j] : i->j 도로 시간
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			arr[a][b] = time;
			arr[b][a] = time;		
		}
		
		isVisited = new boolean[n+1];
		distance = new int[n+1];
		for(int i = 2; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		dijkstra();
		
		int minDist = Integer.MAX_VALUE;
		for(int i = 1; i <=n; i++) {
			if(minDist > distance[i]) {
				minDist = distance[i];
			}
		}
		System.out.println(minDist);
		
//		System.out.println(Arrays.toString(distance));
		
	}// end of main

	private static void dijkstra() {
		for(int i = 0; i < n; i++) {
			int dist = Integer.MAX_VALUE;
			int city = 0;
			for(int j = 1; j <= n; j++) {
				if(!isVisited[j] && dist > distance[j]) {
					dist = distance[j];
					city = j;
				}
			}
			System.out.println("city: " + city);
			isVisited[city] = true;
			
			for(int j = 0; j < arr[city].length; j++) {
				if(arr[city][j] == 0) continue;
				int nextCity = j;
				int nextDist = arr[city][j];
				
				if(distance[nextCity] > distance[city] + nextDist) {
					distance[nextCity] = distance[city] + nextDist;
				}
			}
			
			
		}// end of for(i)
		
	}// end of dijkstra

}// end of class
