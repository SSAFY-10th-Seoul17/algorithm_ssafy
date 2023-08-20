import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1992 {

	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String s = "";

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		sol(0, 0, N);
		System.out.println(sb.toString());
	}

	public static void sol(int x, int y, int n) {
		if (zip(x, y, n)) {
			sb.append(map[x][y]);
			return;
		}

		int size = n / 2;
		sb.append("(");
		sol(x, y, size); // 왼쪽 위
		sol(x, y + size, size); // 오른쪽 위
		sol(x + size, y, size); // 왼쪽 아래
		sol(x + size, y + size, size); // 오른쪽 아래
		sb.append(")");
	}

	// 압축이 가능한지
	public static boolean zip(int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (map[x][y] != map[i][j]) { // 숫자가 동일하지 않으면 압축 불가능
					return false;
				}
			}
		}
		return true;
	}
}
