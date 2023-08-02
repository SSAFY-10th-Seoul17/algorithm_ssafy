import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dir = { {1, -1}, {1, 0}, {1, 1} };
	
	public static boolean inRange(int n, int m, int i, int j, int k) {
		if (0 <= i+dir[k][0] && i+dir[k][0] < n && 0 <= j+dir[k][1] && j+dir[k][1] < m) {
			return true;
		}
		return false;
	}
	
	public static int dfs(int n, int m, int[][] grid, int i, int j, int nowDir, int minFuel, int fuel) {
		if (i == n-1) {
			return Math.min(minFuel, fuel);
		}
		for (int k=0; k<3; k++) {
			if (nowDir != k) {
				if (inRange(n, m, i, j, k)) {
					minFuel = dfs(n, m, grid, i+dir[k][0], j+dir[k][1], k, minFuel, fuel+grid[i+dir[k][0]][j+dir[k][1]]);
				}
			}
		}
		return minFuel;
	}

	public static void main(String[] args) throws IOException {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int grid[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int minFuel = Integer.MAX_VALUE;
		for (int k = 0; k < m; k++) {
			minFuel = Math.min(dfs(n, m, grid, 0, k, -1, minFuel, grid[0][k]), minFuel);
		}
		
		System.out.println(minFuel);
		
	}

}
