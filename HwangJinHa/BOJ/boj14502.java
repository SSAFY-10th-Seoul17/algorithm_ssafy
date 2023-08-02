import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class boj14502 {
	
	static int[][] cells;
	static boolean[][] visited;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int ans = 0;
	
	static void dfs(int y, int x, int depth, int sum) {
		sum += cells[y][x];
		visited[y][x] = true;
		if (depth == 4) {
			ans = (sum > ans)? sum : ans;
			visited[y][x] = false;
			return;
		}
		
		for (int i =  0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			if (!(0 <= yy && yy < cells.length && 0 <= xx && xx < cells[yy].length))
				continue;
			if (visited[yy][xx])
				continue;
			dfs(yy, xx, depth + 1, sum);
		}
		visited[y][x] = false;
	}
	
	static int getSum(int y, int x, int nd) {
		int ans = cells[y][x];
		for (int i = 0; i < 4; i++) {
			if (i == nd) 
				continue;
			ans += cells[y + dy[i]][x + dx[i]];
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		cells = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				cells[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				dfs(i, j, 1, 0);
			}
		}
		
		for (int y = 1; y < n - 1; y++) {
			for (int x = 1; x < m; x++) {
				int result = getSum(y, x, 0);
				ans = (result > ans)? result : ans;
			}
		}
		
		for (int y = 1; y < n - 1; y++) {
			for (int x = 0; x < m - 1; x++) {
				int result = getSum(y, x, 1);
				ans = (result > ans)? result : ans;
			}
		}
		
		for (int y = 1; y < n; y++) {
			for (int x = 1; x < m - 1; x++) {
				int result = getSum(y, x, 2);
				ans = (result > ans)? result : ans;
			}
		}

		for (int y = 0; y < n - 1; y++) {
			for (int x = 1; x < m - 1; x++) {
				int result = getSum(y, x, 3);
				ans = (result > ans)? result : ans;
			}
		}
		System.out.println(ans);
		
		bw.flush();
	}
}
