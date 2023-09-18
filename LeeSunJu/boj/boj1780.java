import java.io.*;
import java.util.*;

public class boj1780 {

	static int N;
	static int[][] map;
	static int[] papers = new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0, 0, N);
		System.out.println(papers[0] + "\n" + papers[1] + "\n" + papers[2]);
	}

	private static void solve(int x, int y, int size) {
		if (sameNum(x, y, size)) {
			if (map[x][y] == -1) {
				papers[0]++;
			} else if (map[x][y] == 0) {
				papers[1]++;
			} else {
				papers[2]++;
			}
			return;
		}

		int n = size / 3;
		solve(x, y, n);
		solve(x, y + n, n);
		solve(x, y + n * 2, n);
		solve(x + n, y, n);
		solve(x + n, y + n, n);
		solve(x + n, y + n * 2, n);
		solve(x + n * 2, y, n);
		solve(x + n * 2, y + n, n);
		solve(x + n * 2, y + n * 2, n);
	}

	private static boolean sameNum(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[x][y] != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
