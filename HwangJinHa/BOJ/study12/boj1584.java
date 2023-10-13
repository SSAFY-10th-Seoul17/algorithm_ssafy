import java.io.*;
import java.util.*;

// 게임 
public class Main {
	static int n, m;
	static int[][] cells = new int[501][501];
	static boolean[][] visited = new boolean[501][501];
	
	static final int[] dy = {0, 1, 0, -1};
	static final int[] dx = {1, 0, -1, 0};
	
	static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.valueOf(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.valueOf(st.nextToken());
			int x1 = Integer.valueOf(st.nextToken());
			int y2 = Integer.valueOf(st.nextToken());
			int x2 = Integer.valueOf(st.nextToken());
			
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			for (int y = y1; y <= y2; y++)
				for (int x = x1; x <= x2; x++)
					cells[y][x] = 1;
		}

		m = Integer.valueOf(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.valueOf(st.nextToken());
			int x1 = Integer.valueOf(st.nextToken());
			int y2 = Integer.valueOf(st.nextToken());
			int x2 = Integer.valueOf(st.nextToken());
			
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			for (int y = y1; y <= y2; y++)
				for (int x = x1; x <= x2; x++)
					visited[y][x] = true;
		}
		
		Deque<Point> q = new ArrayDeque<>();
		q.addLast(new Point(0, 0, 0));
		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if (y < 0 || 500 < y || x < 0 || 500 < x || visited[y][x]) continue;
				if (y == 500 && x == 500) {
					System.out.println(now.cost + ((cells[y][x] == 1)? 1 : 0));
					System.exit(0);
				}
				if (cells[y][x] == 0) {
					q.addFirst(new Point(y, x, now.cost));
//					System.out.println(y + " " + x + " " + (now.cost) + " 추가");
				}
				else {
					q.addLast(new Point(y, x, now.cost+1));
//					System.out.println(y + " " + x + " " + (now.cost + 1) + " 추가");
				}
				visited[y][x] = true;
			}
		}
		System.out.println(-1);
	} // end of main
	
	static class Point {
		int y;
		int x;
		int cost;
		public Point(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}
}
