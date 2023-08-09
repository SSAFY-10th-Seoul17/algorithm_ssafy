import java.io.*;
import java.util.*;
import java.lang.*;

public class boj17484 {
	static int n;
	static int m;
	static int result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dr = {1,1,1}; // 아래,아래,아래
	static int[] dc = {-1,0,1}; // 왼쪽,가운데,오른쪽

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution : dfs(이전방문방향 제거 조건 추가)
		for(int c=0; c<m; c++) {
			dfs(0, c, -1, map[0][c]+0);
		}

		// output
		bw.write(result+"");
		bw.flush();
		bw.close();
	}

	// dfs method
	static void dfs(int r, int c, int d, int res) {
		if(r == n-1) {
			result = res < result ? res : result;
		}
		for(int i=0; i<3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if((d != i) && (nr >= 0 && nr < n && nc >= 0 && nc < m)) {
				dfs(nr, nc, i, map[nr][nc]+res);
			}
		}
	}
}
