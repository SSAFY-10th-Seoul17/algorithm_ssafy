import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [boj] 17484. 진우의 달 여행 (Small)
 */
public class boj17484 {
	static final int DIR = 3; // 이전 방향 사용 금지용.
	static int n, m;
	static int[][] map;
	static int[][][] dp;
	static int minFuel = Integer.MAX_VALUE;
	static int defaultMaxValue;
	static int[] dy = {-1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		defaultMaxValue = 100 * n * m;
		
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[DIR][n][m];
		for(int d = 0; d < DIR; d++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(i == 0) { // 시작 행
						dp[d][i][j] = map[i][j];
						continue;
					}
					// 나머지에 대해서는 최댓값(원소의 최댓값 * 행의 수 * 열의 수)으로 초기화 진행.
					dp[d][i][j] = defaultMaxValue;
				}
			}
		}
		
		// dp 시작.
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < m; j++) {
				for(int d = 0; d < DIR; d++) {
					int beforeX = i - 1;
					int beforeY = j - dy[d];
					if(!isInBoundary(beforeX, beforeY)) { // 이전 칸이 범위 밖이면
						continue;
					}
					
					// 이전에 진행했던 방향으로는 진행 불가.
					dp[d][i][j] = Math.min(dp[d][i][j], dp[(d + 1) % DIR][beforeX][beforeY] + map[i][j]);
					dp[d][i][j] = Math.min(dp[d][i][j], dp[(d + 2) % DIR][beforeX][beforeY] + map[i][j]);
				}
			}
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < DIR; j++) {
				minFuel = Math.min(minFuel, dp[j][n - 1][i]);
			}
		}
		
		bw.write(minFuel + "\n");
		bw.flush();
		bw.close();
	}
	
	public static boolean isInBoundary(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}
}
