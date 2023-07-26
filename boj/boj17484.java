package myAlgo;

import java.util.*;
import java.io.*;

class Dfs {
	static int val= 100000;
	
	static int[] dx = {1, 1, 1};
	static int[] dy = {-1, 0, 1};
	
	static int n;
	static int m;
	
	static void dfs(int x, int y, int pre_direction, int cur_val, int[][] arr) {
//		System.out.println("x : " + x + " y : " + y);
//		System.out.println("curVal : " + cur_val);
		for (int i = 0; i<3; i++) {
			
			if(x == n-1) {
				val = Math.min(cur_val, val);
				continue;
			}
			int tmp_val = cur_val;
			if (i == pre_direction) {
				continue;
			}
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (ny < 0 || ny >= m) {
				continue;
			}
			
			tmp_val += arr[nx][ny];
			dfs(nx, ny, i, tmp_val, arr);
			
		}
		return;
	}
}


public class Boj17484 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Dfs.n = Integer.parseInt(st.nextToken());
		Dfs.m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[Dfs.n][Dfs.m];
		
		for(int i = 0; i<Dfs.n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j<Dfs.m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<Dfs.m; i++) {
			Dfs.dfs(0, i, -1, arr[0][i], arr);
		}
		
		System.out.println(Dfs.val);
		
		
	}
}
