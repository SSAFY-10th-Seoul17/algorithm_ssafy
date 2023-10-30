import java.io.*;
import java.util.*;

public class boj14466_소가길을건너간이유6 {
	static int N, K, R;
	static boolean[][] visited; // 방문처리
	static List<Pos> cows = new ArrayList<>(); // 소위치 좌표 리스트
	static List<Pos>[][] ways; // ways[r][c] = { way1, way2, ... } : (r,c)에 연결되어 있는 길들의 좌표 리스트
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} }; // ↑, ↓, ←, →
	static int res = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		ways = new ArrayList[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				ways[r][c] = new ArrayList<>();
			}
		}
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			ways[sr][sc].add(new Pos(er, ec));
			ways[er][ec].add(new Pos(sr, sc));
		}
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			cows.add(new Pos(r, c));
		}

		// solution
		for(int i=0; i<K; i++) {
			BFS(cows.get(i).r, cows.get(i).c); // 자신과 연결된 목초지 탐색 : 방문처리
			for(int j=0; j<K; j++) {
				if(i == j) continue;
				if(!visited[cows.get(j).r][cows.get(j).c]) res++; // 다른 소가 있는 위치가 아직 방문X 라면 만날 수 없는 경우
			}
		}

		// output
		System.out.println(res/2);
	}

	static void BFS(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		visited = new boolean[N][N];
		queue.offer(new int[] {r, c});
		visited[r][c] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = now[0] + directions[d][0];
				int nc = now[1] + directions[d][1];
				if(!(nr>=0 && nr<N && nc>=0 && nc<N) || visited[nr][nc]) continue;
				// 다음 이동할 위치와 현재 위치 사이에 길이 있다면 -> 이동 불가
				boolean possible = true;
				for(Pos pos : ways[nr][nc]) {
					if(pos.r == now[0] && pos.c == now[1]) possible = false;
				}
				if(possible) {
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
