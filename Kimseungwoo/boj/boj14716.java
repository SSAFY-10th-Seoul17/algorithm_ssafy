package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj14716 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int count;
	private static int[] dx = {-1, 1, -1, 1, -1, 0, 1, 0};
	private static int[] dy = {-1, -1, 1, 1, 0, -1, 0, 1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j<= M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					count++;
					dfs(i, j);
				}
			}
		}
		
		System.out.println(count);
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i = 0; i <dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isPossible(nx, ny)) {
				continue;
			}
			
			dfs(nx, ny);
		}
	}
	
	private static boolean isPossible(int x, int y) {
		if(x < 1 || y < 1 || x > N || y > M) {
			return false;
		}
		
		if(map[x][y] == 0 || visited[x][y] == true) {
			return false;
		}
		
		return true;
	}
}
