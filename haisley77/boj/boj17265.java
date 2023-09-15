import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj17265 {
	static int N, max, min;
	static int[][] dirs = {{1,0},{0,1}};	// 아래 오른쪽
	static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = input.charAt(index);
			}
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(0,0,map[0][0]-'0',' ');
		System.out.println(max + " " + min);
	}
	private static void dfs(int x, int y, int res, char cmd) {
		if (x == N-1 && y == N-1) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			int px = x + dirs[i][0];
			int py = y + dirs[i][1];
			if (px < 0 || px >= N || py < 0 || py >= N) {
				continue;
			}
			char c = map[px][py];
			if (c != '+' && c != '-' && c != '*') {
				dfs(px,py,calc(res,map[px][py]-'0',cmd),cmd);
			} else {
				dfs(px,py,res,map[px][py]);
			}
		}

	}
	
	private static int calc(int res, int num, char op) {
		if (op == '+') {
			return res + num;
		} else if (op == '-') {
			return res - num;
		} else if (op == '*') {
			return res * num;
		} else {
			return 0;
		}
	}

}

