import java.io.*;
import java.util.*;

/*
* 죽음의 구역 -> 방문 X => 이미 방문된 곳으로 처리
* 위험한 구역 -> 가중치 => 가중치 있는 최단거리 => 다익스트라 사용
*  - 이때, 위험한 구역의 가중치를 더하는 조건으로 이전구역이 안전한곳인가?(isSafe) 플래그 확인
* */
public class boj1584 {
	static int N, M;
	static int[][] map;
	static final int DANGER = 1;
	static final int DEATH = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		map = new int[501][501];
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) setMap(new StringTokenizer(br.readLine()), DANGER);
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) setMap(new StringTokenizer(br.readLine()), DEATH);

		// solution
		int[][] distance = dijkstra();
		int res = distance[500][500] == Integer.MAX_VALUE ? -1 : distance[500][500];

		// output
		System.out.println(res);
	}

	static void setMap(StringTokenizer st, int mapType) {
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		for(int r=Math.min(r1, r2); r<=Math.max(r1, r2); r++) {
			for(int c=Math.min(c1, c2); c<=Math.max(c1, c2); c++) {
				map[r][c] = mapType;
			}
		}
	}

	static int[][] dijkstra() {
		int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
		PriorityQueue<Position> queue = new PriorityQueue<>();
		int[][] distance = new int[501][501];
		for(int i=0; i<501; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);
		queue.offer(new Position(0, 0, 0));
		distance[0][0] = 0;

		while(!queue.isEmpty()) {
			Position now = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = now.r + directions[d][0];
				int nc = now.c + directions[d][1];

				if(!(nr>=0 && nr<501 && nc>=0 && nc<501)) continue; //범위확인
				if(distance[nr][nc] <= now.lost + map[nr][nc]) continue; // 방문여부
				if(map[nr][nc] == -1) continue; //죽음구역

				int nextLost = now.lost + map[nr][nc];
				queue.offer(new Position(nr, nc, nextLost));
				distance[nr][nc] = nextLost;
			}
		}

		return distance;
	}

	static class Position implements Comparable<Position> {
		int r, c, lost;

		public Position(int r, int c, int lost) {
			this.r = r;
			this.c = c;
			this.lost = lost;
		}

		@Override
		public int compareTo(Position position) {
			return this.lost - position.lost;
		}
	}
}
