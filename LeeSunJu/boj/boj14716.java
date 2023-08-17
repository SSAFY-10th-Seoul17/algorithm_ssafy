import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14716 {
	static int[][] input;
	private static int M;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (dfs(i, j)) {
					result++;
				}
			}
		}
		System.out.println(result);
	}
	
	private static boolean dfs(int x, int y) {
		if (x < 0 || x >= M || y < 0 || y >= N) {
			return false;
		}

		if (input[x][y] == 1) {
			input[x][y] = 0;
			dfs(x - 1, y); // 상
			dfs(x + 1, y); // 하
			dfs(x, y - 1); // 좌
			dfs(x, y + 1); // 우
			dfs(x - 1, y - 1); // 위에 좌 대각선
			dfs(x + 1, y + 1); // 밑에 우 대각선
			dfs(x - 1, y + 1); // 위에 우 대각선
			dfs(x + 1, y - 1); // 밑에 좌 대각선
			return true;
		}

		return false;
	}
}
