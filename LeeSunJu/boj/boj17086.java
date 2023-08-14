import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17086 {
	static int N;
	static int M;
	static int[][] map;
	static int result;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(),  " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					result = Math.max(result, bfs(i, j));
				}
			}
		}
		System.out.println(result);
	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{x, y, 0});
		boolean[][] visited = new boolean[N][M];
		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int nx = tmp[0] + dir[i][0];
				int ny = tmp[1] + dir[i][1];
				int cnt = tmp[2] + 1;

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == 1) {
					return cnt;
				} else {
					visited[nx][ny] = true;
					queue.add(new int[]{nx, ny, cnt});
				}
			}
		}
		return 0;
	}
}
