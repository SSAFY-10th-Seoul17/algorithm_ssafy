import java.io.*;
import java.util.*;

public class boj18430_무기공학 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int res = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		visited = new boolean[N][M];
		DFS(0, 0);

		// output
		System.out.println(res == Integer.MIN_VALUE ? 0 : res);
	}

	static void DFS(int idx, int score) {
		if(idx == N * M) {
			res = Math.max(res, score);
			return;
		}

		int r = idx / M;
		int c = idx % M;

		if(!visited[r][c]) {
			if((r-1)>=0 && (c-1)>=0 && !visited[r-1][c] && !visited[r][c-1]) { // ↑, ←
				visited[r][c] = visited[r-1][c] = visited[r][c-1] = true;
				DFS(idx+1, score + map[r-1][c] + 2 * map[r][c] + map[r][c-1]);
				visited[r][c] = visited[r-1][c] = visited[r][c-1] = false;
			}
			if((r-1)>=0 && (c+1)<M && !visited[r-1][c] && !visited[r][c+1]) { // ↑, →
				visited[r][c] = visited[r-1][c] = visited[r][c+1] = true;
				DFS(idx+1, score + map[r-1][c] + 2 * map[r][c] + map[r][c+1]);
				visited[r][c] = visited[r-1][c] = visited[r][c+1] = false;
			}
			if((r+1)<N && (c-1)>=0 && !visited[r+1][c] && !visited[r][c-1]) { // ↓, ←
				visited[r][c] = visited[r+1][c] = visited[r][c-1] = true;
				DFS(idx+1, score + map[r+1][c] + 2 * map[r][c] + map[r][c-1]);
				visited[r][c] = visited[r+1][c] = visited[r][c-1] = false;
			}
			if((r+1)<N && (c+1)<M && !visited[r+1][c] && !visited[r][c+1]) { // ↓, →
				visited[r][c] = visited[r+1][c] = visited[r][c+1] = true;
				DFS(idx+1, score + map[r+1][c] + 2 * map[r][c] + map[r][c+1]);
				visited[r][c] = visited[r+1][c] = visited[r][c+1] = false;
			}
		}
		DFS(idx+1, score);
	}
}
