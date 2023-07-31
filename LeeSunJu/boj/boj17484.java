import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17484 {
	static int n, m;
	static int result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dx = {1, 1, 1};
	static int[] dy = {-1, 0, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		// map 채우기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			dfs(0, i, -1, map[0][i]); // 방향은 0(왼쪽 대각선 아래), 1(하), 2(오른쪽 대각선 아래)
		}

		System.out.println(result);
	}

	public static void dfs(int x, int y, int d, int total) { // x, y, 이전에 선택한 방향, 연료의 양
		if (x == n - 1) { // 마지막 행일 경우
			result = Math.min(result, total); // 최소 연료의 양
			return;
		}

		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (d == i || nx < 0 || nx >= n || ny < 0 || ny >= m) { // 이전 방향이 현재 움직일 방향과 같거나 범위가 벗어난 경우
				continue;
			}

			dfs(nx, ny, i, total + map[nx][ny]);
		}
	}
}
