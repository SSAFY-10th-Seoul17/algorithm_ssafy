package study8_3;

import java.io.*;
import java.util.*;

// 아기상어2
public class boj17086 {
	static int n, m;
	static int[][] cells;
	static Queue<int[]> q = new LinkedList<int[]>();
	// 좌 부터 반시계 
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static final int lim = 50 * 50 + 1;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		cells = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				cells[i][j] = Integer.valueOf(st.nextToken());
				if (cells[i][j] == 1) {
					cells[i][j] = 0;
					q.add(new int[] {i, j});
				}
				else
					cells[i][j] = lim;
			}
		}
		
		bfs();
		System.out.println(ans);

	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int[] loc = q.poll();
			int next = cells[loc[0]][loc[1]] + 1;
			boolean added = false;
			for(int i = 0; i < 8; i++) {
				int yy = loc[0] + dy[i];
				int xx = loc[1] + dx[i];
				
				if (!(0 <= yy && yy < n && 0 <= xx && xx < m))
					continue;
				if (next >= cells[yy][xx])
					continue;
				
				cells[yy][xx] = next;
				q.offer(new int[] {yy, xx});
				added = true;
			}
			if (added)
				ans = Math.max(ans, next);
		}
	}
}
