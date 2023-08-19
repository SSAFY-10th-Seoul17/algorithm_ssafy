import java.io.*;
import java.util.*;

public class boj14176 {
	static int M, N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1} };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		for(int r=0; r<M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution : 한번 글자를 발견하면 그 글자와 연결된 모든 글자를 살펴볼 때 까지가 카운트 1 -> 한글자에서 "퍼져나가는" 형식 -> DFS
		int cnt = 0;
		for(int r=0; r<M; r++) {
			for(int c=0; c<N; c++) {
				if((map[r][c] == 1) && !visited[r][c]) {
					cnt++;
					dfs(r, c);
				}
			}
		}

		// output
		System.out.println(cnt);
	}

	static void dfs(int r, int c) { // 이미 카운트된 글자임을 체크만 해주는 역할
		if(map[r][c] == 0) {
			return;
		}
		for(int d=0; d<8; d++) {
			int nr = r+directions[d][0];
			int nc = c+directions[d][1];
			if(nr>=0 && nr<M && nc>=0 && nc<N && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}
