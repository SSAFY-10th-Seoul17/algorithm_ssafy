import java.io.*;
import java.util.*;

public class boj17086 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1} };
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == 1) continue;
				max = Math.max(max, count(new Shark(r, c, 0)));
			}
		}

		// output
		System.out.println(max);
	}

	static int count(Shark shark) {
		Queue<Shark> sharks = new ArrayDeque<>();
		sharks.offer(shark);
		visited = new boolean[N][M];
		visited[shark.r][shark.c] = true;

		while(!sharks.isEmpty()) {
			Shark now = sharks.poll();

			for(int d=0; d<8; d++) {
				int nr = now.r+directions[d][0];
				int nc = now.c+directions[d][1];

				if(!(nr>=0 && nr<N && nc>=0 && nc<M) || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) return now.dist+1;

				sharks.offer(new Shark(nr, nc, now.dist+1));
				visited[nr][nc] = true;
			}
		}
		return 0;
	}
}

class Shark {
	int r; int c; int dist;

	public Shark(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}
