import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2096 {
	private static int[] dirs = {-1,0,1};
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][3];
		int[][][] dp = new int[N][3][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < 3; j++, index += 2) {
				map[i][j] = s.charAt(index) - '0';
				dp[i][j][0] = Integer.MIN_VALUE;
				dp[i][j][1] = Integer.MAX_VALUE;
			}
		}
		for (int j = 0; j < 3; j++) {
			dp[0][j][0] = dp[0][j][1] = map[0][j];
		}
		go(dp,map,1);
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (max < dp[N-1][i][0]) {
				max = dp[N-1][i][0];
			}
			if (min > dp[N-1][i][1]) {
				min = dp[N-1][i][1];
			}
		}
		System.out.println(max + " " + min);
	}

	private static void go(int[][][] dp, int[][] map, int r) {
		if (r == N) return;
		for (int c = 0; c < 3; c++) {
			for (int i = 0; i < 3; i++) {
				int pc = c + dirs[i];
				if (pc >= 0 && pc < 3) {
					dp[r][c][0] = Math.max(dp[r][c][0],dp[r-1][pc][0] + map[r][c]);
					dp[r][c][1] = Math.min(dp[r][c][1],dp[r-1][pc][1] + map[r][c]);
				}
			}
		}
		go(dp,map,r+1);
	}
}
