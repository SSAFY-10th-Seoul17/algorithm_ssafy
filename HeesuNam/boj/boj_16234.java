import java.io.*;
import java.util.*;

public class Main {
	private static int N, L, R;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	private static boolean[][] visited;
	private static Queue<Plot> queue = new ArrayDeque<>();
	private static int[][] countries;
	private static Stack<Plot> team;

	static class Plot {
		int y;
		int x;

		public Plot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Plot [y=" + y + ", x=" + x + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		countries = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				countries[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		team = new Stack<>();
		while (true) {
			visited = new boolean[N][N];
			boolean isMove = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						// flood fill
						int sum = union(i, j);
						// 인구이동해야하는지 검사
						if (team.size() > 1) {
							isMove = true;
							move(sum);
						}
						else team.clear();
					}
				}
			}
			// 인구 이동한적있는지 검사
			if (!isMove)
				break;
			day++;
		}
		System.out.println(day);
	}// end of main

	private static void move(int sum) {
		int people = sum / team.size();
		while (!team.isEmpty()) {
			Plot c = team.pop();
			countries[c.y][c.x] = people;
		}
	}

	private static int union(int y, int x) {
		visited[y][x] = true;
		int sum = countries[y][x];
		Plot start = new Plot(y, x);
		team.add(start);
		queue.offer(start);
		while (!queue.isEmpty()) { // 연합찾기
			Plot cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				// 범위안에 있고 방문하지 않았고 인구차가 L이상 R이하인 국가찾기
				if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx] && chkDiff(cur.y, cur.x, ny, nx)) {
					visited[ny][nx] = true;
					sum += countries[ny][nx];
					Plot nxt = new Plot(ny, nx);
					team.add(nxt);
					queue.offer(nxt);
				}
			}
		}
		return sum;
	}

	private static boolean chkDiff(int y, int x, int ny, int nx) {
		int diff = Math.abs(countries[y][x] - countries[ny][nx]);
		return L <= diff && diff <= R;
	}
} // end of class
