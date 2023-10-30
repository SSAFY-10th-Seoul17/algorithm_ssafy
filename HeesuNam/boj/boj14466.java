import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, R, mapSize;
	private static int[][] map;
	private static Main.Plot[] cows;
	private static final int[] dy = { -2, 0, 2, 0 };
	private static final int[] dx = { 0, 2, 0, -2 };
	private static final int[] rdy = { -1, 0, 1, 0 };
	private static final int[] rdx = { 0, 1, 0, -1 };
	private static Queue<Plot> queue = new ArrayDeque<>();

	static class Plot {
		int y;
		int x;

		public Plot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// 길표시하기 위해 2*(N+2) 맵늘리기
		mapSize = 2 * (N + 2);
		map = new int[mapSize][mapSize];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken()) * 2;
			int x1 = Integer.parseInt(st.nextToken()) * 2;
			int y2 = Integer.parseInt(st.nextToken()) * 2;
			int x2 = Integer.parseInt(st.nextToken()) * 2;
			// 목초지 사이에 길표시하기
			int ry = y1 - ((y1 - y2) / 2);
			int rx = x1 - ((x1 - x2) / 2);
			map[ry][rx] = -1;
		}
		cows = new Plot[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) * 2;
			int x = Integer.parseInt(st.nextToken()) * 2;
			cows[i] = new Plot(y, x);
		}
		// 영역표시
		int group = 1;
		for (int i = 2; i <= 2 * N; i += 2) {
			for (int j = 2; j <= 2 * N; j += 2) {
				if (map[i][j] == 0) {
					grouping(i, j, group);
					group++;
				}
			}
		}
		// 소의 영역검사
		int[] groupCow = new int[group];
		for (int i = 0; i < K; i++) {
			groupCow[map[cows[i].y][cows[i].x]]++;
		}
		int ans = 0;
		// 영역이 다를 경우 길건너야한다고 판단
		for (int i = 1; i < group; i++) {
			for (int j = i+1; j < group; j++) {
				ans += groupCow[i]*groupCow[j];				
			}
		}
		System.out.println(ans);

	}// end main

	private static void grouping(int y, int x, int group) {
		queue.offer(new Plot(y, x));
		map[y][x] = group;
		while (!queue.isEmpty()) {
			Plot cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				// 두칸씩이동
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				// 벽(이동 한칸 전) 검사
				if (isRoad(cur.y + rdy[i], cur.x + rdx[i]) && rangeChk(ny / 2, nx / 2) && map[ny][nx] == 0) {
					map[ny][nx] = group;
					queue.add(new Plot(ny, nx));
				}
			}
		}
	}

	private static boolean isRoad(int y, int x) {
		return map[y][x] != -1;
	}

	private static boolean rangeChk(int y, int x) {
		return 1 <= y && y <= N && 1 <= x && x <= N;
	}

}// end class
