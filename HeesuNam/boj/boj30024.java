import java.io.*;
import java.util.*;

// pq사용
// pq에 테두리 넣기
// K번 반복
// pop한 상하좌우노드 넣기(visited로 넣었는지 체크)
public class Main {
	private static int N, M, K;
	private static boolean[][] visited;
	private static PriorityQueue<Corn> pq = new PriorityQueue<Corn>((c1, c2) -> Integer.compare(c1.value, c2.value)*-1);
	private static int[][] map;
	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };

	static class Corn {
		int y;
		int x;
		int value;

		public Corn(int y, int x, int value) {
			super();
			this.y = y;
			this.x = x;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 2][M + 2];
		map = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i == 1 || i == N || j == 1 || j == M) { // 테두리 값 넣어주기
					visited[i][j] = true;
					pq.add(new Corn(i, j, map[i][j]));
				}
			}
		}
		K = Integer.parseInt(br.readLine());
		System.out.println(getCorn());
	} // end of main

	private static String getCorn() {
		StringBuilder sb = new StringBuilder();
		for (int cnt = 0; cnt < K && !pq.isEmpty(); cnt++) {
			Corn cur = pq.poll();
			sb.append(cur.y).append(" ").append(cur.x).append("\n");
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (chkRange(ny, nx) && !visited[ny][nx]) {
					visited[ny][nx] = true;
					pq.add(new Corn(ny, nx, map[ny][nx]));
				}
			}
		}
		return sb.toString();
	}

	private static boolean chkRange(int ny, int nx) {
		return 1 <= ny && ny <= N && 1 <= nx && nx <= M;
	}
} // end of class
