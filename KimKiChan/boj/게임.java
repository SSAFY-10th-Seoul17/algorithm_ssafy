package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 게임 {
	private static int[][] map;
	private static int[][] dxdy = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 위험한 지역
		map = new int[501][501]; // 안전 구역 0
		// 위험한 구역의 수
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) { // 위험 구역 1
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int upX = Math.min(x1, x2);
			int downX = Math.max(x1, x2);
			int leftY = Math.min(y1, y2);
			int rightY = Math.max(y1, y2);
			
			// 위험 구역 설정
			for(int j = upX; j <= downX; j++ ) {
				for(int k = leftY; k <= rightY; k++) {
					map[j][k] = 1;
				}
			}

		}
		// 죽음의 구역의 수
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) { // 죽음 구역 -1
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int upX = Math.min(x1, x2);
			int downX = Math.max(x1, x2);
			int leftY = Math.min(y1, y2);
			int rightY = Math.max(y1, y2);
			
			// 죽음 구역 설정
			for(int j = upX; j <= downX; j++ ) {
				for(int k = leftY; k <= rightY; k++) {
					map[j][k] = -1;
				}
			}
		}
		
		bfs();
		
	}

	private static void bfs() { // 다익스트라
		// 시작 0, 0 도착 500, 500
		boolean[][] visited = new boolean [501][501];
		
		// 도달까지 필요한 최소 생명력
		int[][] hp = new int[501][501];
		for(int i = 0; i < 501; i++) {
			Arrays.fill(hp[i], Integer.MAX_VALUE);
		}
		hp[0][0] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[2], b[2]))); // x, y, 생명력
		pq.offer(new int[] {0, 0, 0});
		
		while(!pq.isEmpty()) {
			int x = pq.peek()[0];
			int y = pq.peek()[1];
			pq.poll();
			
			if(visited[x][y]) continue;
			visited[x][y] = true;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dxdy[i][0];
				int ny = y + dxdy[i][1];
				if(nx >= 0 && ny >= 0 && nx < 501 && ny < 501) {
					if(map[nx][ny] >= 0 && hp[nx][ny] > hp[x][y] + map[nx][ny]) {
						hp[nx][ny] = hp[x][y] + map[nx][ny];
						pq.offer(new int[] {nx, ny, hp[nx][ny]});
					}
				}
			}

		} // end of dq
		
		if(hp[500][500] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(hp[500][500]);
		}
		
		
	}
}
