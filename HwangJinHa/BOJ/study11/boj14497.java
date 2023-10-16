package study8_5;

import java.io.*;
import java.util.*;

// 주난의 난 
public class boj14497 {
	static int n, m;
	static int sy, sx;
	static int ey, ex;
	static char[][] cells;
	
	static final int[] dy = {0, 1, 0, -1};
	static final int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		cells = new char[n][];

		st = new StringTokenizer(br.readLine());
		sy = Integer.valueOf(st.nextToken()) - 1;
		sx = Integer.valueOf(st.nextToken()) - 1;
		ey = Integer.valueOf(st.nextToken()) - 1;
		ex = Integer.valueOf(st.nextToken()) - 1;

		for (int i = 0; i < n; i++) 
			cells[i] = br.readLine().toCharArray();
		
		int cnt = 1;
		while (!find()) {
			cnt++;
		}

		System.out.println(cnt);
		
	} // end of main

	static boolean[][] visited;
	private static boolean find() {
		visited = new boolean[n][m];
		
		Queue<Loc> q = new LinkedList<Loc>();
		q.add(new Loc(sy, sx));
		Queue<Loc> erase = new LinkedList<Loc>();
		while (!q.isEmpty()) {
			Loc now = q.poll();
			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if (y == ey && x == ex) return true;
				
				if (y < 0 || n <= y || x < 0 || m <= x) continue;
				if (visited[y][x]) continue;
				if (cells[y][x] == '1') {
					erase.add(now);
					continue;
				}

				visited[y][x] = true;
				q.add(new Loc(y, x));
			}
		}
		
		while(!erase.isEmpty()) {
			Loc now = erase.poll();
			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				
				if (y < 0 || n <= y || x < 0 || m <= x) continue;
				cells[y][x] = '0';
			}
		}

		return false;
	}
	
	static class Loc {
		int y;
		int x;
		Loc() {}
		Loc(int y, int x) {this.y = y; this.x = x;}
	}
} // end of class
