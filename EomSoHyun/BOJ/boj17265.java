import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N, min, max;
	static char[][] map;
	static int[] dx = {0 , 1};
	static int[] dy = {1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx+=2) {
				map[i][j] = line.charAt(idx);
			}
		}
		
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		dfs(0, 0, map[0][0] - '0', ' ');
		System.out.println(max + " " + min);
		
	}
	
	public static void dfs(int x, int y, int num, char op) {
		if (x == N-1 && y == N-1) {
			min = Math.min(min, num);
			max = Math.max(max, num);
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < N && ny < N) {
				switch (map[nx][ny]) {
				case '*':
					dfs(nx, ny, num, '*');
					break;
				case '+':
					dfs(nx, ny, num, '+');
					break;
				case '-':
					dfs(nx, ny, num, '-');
					break;
				default:
					if (op == '*') {
						dfs(nx, ny, num * (map[nx][ny] - '0'), ' ');
					} else if (op == '+') {
						dfs(nx, ny, num + (map[nx][ny] - '0'), ' ');
					} else if (op == '-') {
						dfs(nx, ny, num - (map[nx][ny] - '0'), ' ');
					}
					break;
	
				}
			}
		}
	}

}
