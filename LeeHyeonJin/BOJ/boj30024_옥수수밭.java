import java.io.*;
import java.util.*;

/**
 * 아이디어 : PriorityQueue 를 사용한 BFS
 *  1. 바깥을 돌아다니다가 가장 큰수부터 "BFS" 시작 -> 더 큰 가치를 가진 옥수수 수확
 *  2. 다음번에 수확할 수 있는 가치보다 바깥과 인접한 칸의 옥수수의 가치가 더 크면 거기서 다시 BFS 시작
 *     - 이때, "PriorityQueue" 에 미리 경계선의 옥수수들을 넣어두면, 자동정렬해주니까 (경계선 vs 다음갈곳)을 비교해줄 것
 */
public class boj30024_옥수수밭 {
	static int N, M, K;
	static int[][] map;
	static PriorityQueue<Corn> corns = new PriorityQueue<>((a,b) -> b.val - a.val);
	static boolean[][] visited;
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} }; // ↑, ↓, ←, →
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(r == 0 || c == 0 || r == N-1 || c == M-1) {
					corns.offer(new Corn(r, c, map[r][c]));
					visited[r][c] = true;
				}
			}
		}
		K = Integer.parseInt(br.readLine());

		// solution
		BFS();

		// output
		System.out.println(sb.toString());
	}

	static void BFS() {
		for(int k=0; k<K; k++) {
			Corn corn = corns.poll();
			sb.append(corn.r+1).append(" ").append(corn.c+1).append("\n");
			for(int d=0; d<4; d++) {
				int nr = corn.r + directions[d][0];
				int nc = corn.c + directions[d][1];
				if(!(nr>=0 && nr<N && nc>=0 && nc<M) || visited[nr][nc]) continue;
				corns.offer(new Corn(nr, nc, map[nr][nc]));
				visited[nr][nc] = true;
			}
		}
	}

	static class Corn {
		int r, c, val;

		public Corn(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
}
