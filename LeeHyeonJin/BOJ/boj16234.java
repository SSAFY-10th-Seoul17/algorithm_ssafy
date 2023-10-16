import java.io.*;
import java.util.*;

public class boj16234 {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static boolean isDone;
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
	static Queue<Pair> info = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		int res = 0;
		while(true) {
			visited = new boolean[N][N];
			isDone = true;

			// (1) unite
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(visited[r][c]) continue;
					unite(r, c);
				}
			}

			// (2) check
			if(isDone) break;

			// (3) move
			while(!info.isEmpty()) {
				Pair pair = info.poll();
				move(pair.newPopulation, pair.units);
			}

			res++;
		}

		// output
		System.out.println(res);
	}

	static void unite(int r, int c) {
		Queue<int[]> res = new ArrayDeque<>();
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;

		int population = 0, cnt = 0;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			res.offer(new int[] {now[0], now[1]});
			population += map[now[0]][now[1]];
			cnt++;
			for(int d=0; d<4; d++) {
				int nr = now[0] + directions[d][0];
				int nc = now[1] + directions[d][1];
				if(!(nr>=0 && nr<N && nc>=0 && nc<N) || visited[nr][nc]) continue;
				int diff = Math.abs(map[nr][nc] - map[now[0]][now[1]]);
				if(diff >= L && diff <= R) {
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}

		if(cnt > 1) {
			info.offer(new Pair(population/cnt, res));
			isDone = false;
		}
	}

	static void move(int key, Queue<int[]> value) {
		while(!value.isEmpty()) {
			int[] now = value.poll();
			map[now[0]][now[1]] = key;
		}
	}

	static class Pair {
		int newPopulation;
		Queue<int[]> units;

		public Pair(int newPopulation, Queue<int[]> units) {
			this.newPopulation = newPopulation;
			this.units = units;
		}
	}
}
