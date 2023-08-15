import java.io.*; 
import java.util.*;

public class boj14716 {
	static boolean[][] arr;
	static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
	static int N, M, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // x
		N = Integer.parseInt(st.nextToken()); // y
		arr = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			}
		}
		cnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j]) {
					bfs(i, j);
				}
			}
		}
		System.out.println(cnt);
	}

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { x, y });
		arr[x][y] = false;
		while (!q.isEmpty()) {
			int[] nextXY = q.poll();
			int nextX = nextXY[0];
			int nextY = nextXY[1];
			for (int[] dxy : dir) {
				int dx = nextX + dxy[0];
				int dy = nextY + dxy[1];
				if (0 <= dx && dx < M && 0 <= dy && dy < N && arr[dx][dy]) {
					arr[dx][dy] = false;
					q.add(new int[] { dx, dy });
				}
			}
		}
		cnt++;
	}
}