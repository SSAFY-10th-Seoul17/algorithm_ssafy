import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Location {
		int x1;
		int y1;
		int x2;
		int y2;
		int cnt;

		public Location(int x1, int y1, int x2, int y2, int cnt) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
		}

	}

	private static int[][] button = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };// 네개의 버튼(왼, 오, 위, 아래)
	private static char[][] map;
	private static int n;
	private static int m;
	private static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		map = new char[n][m]; // 보드
		isVisited = new boolean[n][m]; // 보드
		int ax = -1;
		int ay = -1;
		int bx = -1;
		int by = -1;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'o') {
					if (cnt == 1) {
						// 동전 2 위치
						bx = i;
						by = j;
					} else {
						// 동전1 위치
						ax = i;
						ay = j;
						cnt++;
					}
				}
			}
		}

		int answer = bfs(ax, ay, bx, by);
		System.out.println(answer);

	}// end main

	// 두 동전 중 하나만 보드에서 떨어뜨리기 위해 최소 몇번 버튼을 눌러야하나?

	public static int bfs(int x1, int y1, int x2, int y2) {
		Queue<Location> queue = new ArrayDeque<Location>();
		queue.offer(new Location(x1, y1, x2, y2, 0));

		while (!queue.isEmpty()) {
			Location current = queue.poll();

			if (current.cnt == 10) {
				return -1;
			}

			for (int i = 0; i < 4; i++) {
				int drop = 0;
				int nx1 = current.x1 + button[i][0];
				int ny1 = current.y1 + button[i][1];
				int nx2 = current.x2 + button[i][0];
				int ny2 = current.y2 + button[i][1];

				// 이동하려는 방향에 칸이 없으면 동전은 보드 밖으로 떨어짐
				if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m) {
					drop++;
				}
				if (nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m) {
					drop++;
				}

				if (drop == 1) { // 하나만 떨어짐
					return current.cnt + 1;
				}
				if (drop == 2) {
					continue; // 다 떨어진 경우
				}

				// 벽 - 벽
				if (map[nx1][ny1] == '#' && map[nx2][ny2] == '#') {
					continue;
				}
				// 벽 - 이동
				if (map[nx1][ny1] == '#') {
					queue.offer(new Location(current.x1, current.y1, nx2, ny2, current.cnt + 1));
					continue;
				}
				// 이동 - 벽
				if (map[nx2][ny2] == '#') {
					queue.offer(new Location(nx1, ny1, current.x2, current.y2, current.cnt + 1));
					continue;
				}
				// 이동 - 이동
					queue.offer(new Location(nx1, ny1, nx2, ny2, current.cnt + 1));

			} // for 끝
		} // while 끝
		return -1;

	}// end bfs

}// end class
