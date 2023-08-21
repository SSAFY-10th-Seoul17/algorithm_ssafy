package boj;

import java.util.*;
import java.io.*;


/*
 * 5×5 크기의 숫자판이 있다. 각각의 칸에는 숫자(digit, 0부터 9까지)가 적혀 있다.
 * 이 숫자판의 임의의 위치에서 시작해서, 인접해 있는 네 방향으로 다섯 번 이동하면서, 
 * 각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다. 
 * 이동을 할 때에는 한 번 거쳤던 칸을 다시 거쳐도 되며, 
 * 0으로 시작하는 000123과 같은 수로 만들 수 있다.
 * 
 * */
public class boj2210 {
	
	private static HashSet<String> hSet = new HashSet<>();
	
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, -1, 0, 1};
	
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		map = new int[5][5];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j<5; j++) {
				dfs(i, j, new String(map[i][j] + ""));
			}
		}
		
		System.out.println(hSet.size());
	}
	
	private static void dfs(int x, int y, String s) {
		if (s.length() == 6) {
			hSet.add(s);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isPossible(nx, ny)) {
				continue;
			}
			dfs(nx, ny, s+map[nx][ny]);
		}
		
	}
	
	private static boolean isPossible(int x, int y) {
		if(x < 0 || y < 0 || x >= 5 || y >= 5) {
			return false;
		}
		return true;
	}
}
