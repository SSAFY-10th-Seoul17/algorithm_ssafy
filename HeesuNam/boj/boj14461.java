import java.io.*;
import java.util.*;

public class Main {
	static class Cow {
		int y;
		int x;
		int move;
		int time;

		public Cow(int y, int x, int move, int time) {
			super();
			this.y = y;
			this.x = x;
			this.move = move;
			this.time = time;
		}
	}

	private static int N, T;
	private static int[][] map;
	private static final int[] dy = { -1, 0, 1, 0 };
	private static final int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dijk();
	} // end of main

	private static void dijk() {
		PriorityQueue<Cow> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
		boolean[][][] visited = new boolean[N][N][3];
		pq.offer(new Cow(0, 0, 0, 0));
		while (!pq.isEmpty()) {
			Cow cur = pq.poll();
			if (visited[cur.y][cur.x][cur.move])
				continue;
			visited[cur.y][cur.x][cur.move] = true;
			if (cur.y == N - 1 && cur.x == N - 1) {
				System.out.println(cur.time);
				return;
			}
			int nxtMove = (cur.move + 1) % 3;
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (rangeChk(ny, nx) && !visited[ny][nx][nxtMove]) {
					if (nxtMove == 0) {
						pq.offer(new Cow(ny, nx, nxtMove, cur.time + T + map[ny][nx]));
					} else {
						pq.offer(new Cow(ny, nx, nxtMove, cur.time + T));
					}
				}
			}
		}
	}

	private static boolean rangeChk(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
} // end of class
