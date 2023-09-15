import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17265 {

	static int N;
	static char[][] map;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = s.charAt(idx);
			}
		}

		dfs(0, 0, 0, 0, ' ');

		System.out.println(max + " " + min);
	}

	private static void dfs(int x, int y, int depth, int total, char op) {
		if (x >= N || y >= N) { // 오른쪽 or 아래로만 가기 때문에 N에 대해서만 범위 체크
			if (depth == N * 2 - 1) { // 최단거리일 때만 값 갱신할지 정해줌.
				if (max < total) { // 최대값 구하기
					max = total;
				}
				if (min > total) { // 최소값 구하기
					min = total;
				}
			}
			return;
		}

		int n = 0;
		char tmpOp = ' ';
		if (Character.isDigit(map[x][y])) { // 숫자가 나오면 이전 수와 연산 시행
			n = map[x][y] - '0';
			switch (op) {
				case '+':
					n += total;
					break;
				case '-':
					n = total - n;
					break;
				case '*':
					n *= total;
					break;
			}
		} else { // 연산자가 나오면 다음 수와 연산할 수 있도록 연산자 매개변수로 넘겨줌.
			tmpOp = map[x][y];
			n = total; // 이전 연산결과 저장
		}

		dfs(x + 1, y, depth + 1, n, tmpOp);
		dfs(x, y + 1, depth + 1, n, tmpOp);
	}
}
