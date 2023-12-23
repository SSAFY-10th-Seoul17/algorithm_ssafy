import java.io.*;
import java.util.*;

public class Main {
	static final int[] DY = { -1, 0, 1, 0 };
	static final int[] DX = { 0, 1, 0, -1 };
	static int[][] roads, visited;
	static int m, n;

	static class Road {
		int y;
		int x;
		int cost;

		public Road(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		roads = new int[m + 2][n + 2];
		visited = new int[m + 2][n + 2];
		Arrays.fill(roads[0], -1);
		Arrays.fill(roads[m + 1], -1);
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			roads[i][0] = -1;
			for (int j = 1; j <= n; j++) {
				roads[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = Integer.MAX_VALUE;
			}
			roads[i][n + 1] = -1;
		}
		if(roads[1][1]==-1 || roads[m][n]==-1) {
			System.out.println(-1);
		}
		else{
			reconstruction();
		}
	} // end of main

	private static void reconstruction() {
		PriorityQueue<Road> pq = new PriorityQueue<Road>((r1, r2) -> r1.cost - r2.cost);
		pq.offer(new Road(1, 1, roads[1][1]));
		while (!pq.isEmpty()) {
			Road cur = pq.poll();
			if (cur.cost > visited[cur.y][cur.x])
				continue;
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + DY[i];
				int nx = cur.x + DX[i];
				if (roads[ny][nx] == -1) {
					continue;
				}
				int d = cur.cost + roads[ny][nx];
				if (visited[ny][nx] > d) {
					visited[ny][nx] = d;
					pq.offer(new Road(ny, nx, d));
				}
			}
		}

		System.out.println(visited[m][n] == Integer.MAX_VALUE?-1:visited[m][n]);
	}
} // end of class
