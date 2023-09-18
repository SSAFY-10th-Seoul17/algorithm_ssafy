import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어 
 * bfs 
 * - 지훈이, 불 위치 
 * - 지훈 이동 
*   - 지훈이 위치가 불번진곳인지 확인
 *  - 불이 있거나 벽이면 X 
   * - 지훈 탈출 
   *    - R,C==0 or == R+1,C+1
   * - 새롭게 이동한 위치가 없으면 impossible
 * - 불 이동 
 *  - 벽이면 X, 번진 불만 큐에 넣기 
 */
public class Main {
	static class Plot {
		int r;
		int c;

		public Plot(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	private static int R;
	private static int C;
	private static char[][] map;
	private static int[] dr = { -1, 0, 1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };
	private static Queue<Plot> jihoonQueue;
	private static Queue<Plot> fireQueue;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 2][C + 2];

		jihoonQueue = new ArrayDeque<>();
		fireQueue = new ArrayDeque<>();
		visited = new boolean[R + 2][C + 2];

		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = str.charAt(j - 1);
				switch (map[i][j]) {
				case 'J':
					jihoonQueue.offer(new Plot(i, j));
					visited[i][j] = true;
					map[i][j] = '.';
					break;
				case 'F':
					fireQueue.offer(new Plot(i, j));
					break;
				}
			}
		}

		maze();
	} // end of main

	private static void maze() {
		int time = 1;
		while (true) {
			int exist = jihoonQueue.size();
			for (int i = 0; i < exist; i++) {
				Plot jihoon = jihoonQueue.poll();
				if (map[jihoon.r][jihoon.c] == 'F')
					continue;
				for (int j = 0; j < 4; j++) {
					int jnxtR = jihoon.r + dr[j];
					int jnxtC = jihoon.c + dc[j];
					if (isExcape(jnxtR, jnxtC)) {
						System.out.println(time);
						return;
					}
					if (rangeChk(jnxtR, jnxtC) && !visited[jnxtR][jnxtC]) {
						visited[jnxtR][jnxtC] = true;
						jihoonQueue.offer(new Plot(jnxtR, jnxtC));
					}
				}
			}
			if(jihoonQueue.isEmpty()) {
				System.out.println("IMPOSSIBLE ");
				return;
			}
			spreadFire(fireQueue.size());
			time++;
		}
	}

	private static void spreadFire(int exist) {
		for (int i = 0; i < exist; i++) {
			Plot fcur = fireQueue.poll();
			for (int j = 0; j < 4; j++) {
				int fnxtR = fcur.r + dr[j];
				int fnxtC = fcur.c + dc[j];
				if (rangeChk(fnxtR, fnxtC)) {
					map[fnxtR][fnxtC] = 'F';
					fireQueue.offer(new Plot(fnxtR, fnxtC));
				}
			}
		}
	}

	private static boolean isExcape(int jnxtR, int jnxtC) {
		return jnxtR == 0 || jnxtC == 0 || jnxtR == R + 1 || jnxtC == C + 1;
	}

	private static boolean rangeChk(int nr, int nc) {
		return 1 <= nr && nr <= R && 1 <= nc && nc <= C && map[nr][nc] == '.';
	}
}
