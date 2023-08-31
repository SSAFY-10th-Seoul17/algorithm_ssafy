import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map, danger, death;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());  // 위험 구역의 수
		danger = new int[N][4];  // 위험 구역
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			danger[i][0] = Math.min(x1, x2);
			danger[i][1] = Math.max(x1, x2);
			danger[i][2] =  Math.min(y1, y2);
			danger[i][3] =  Math.max(y1, y2);
		}
				
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());  // 죽음 구역의 수
		death = new int[M][4];  // 죽음 구역
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			death[i][0] = Math.min(x1, x2);
			death[i][1] = Math.max(x1, x2);
			death[i][2] =  Math.min(y1, y2);
			death[i][3] =  Math.max(y1, y2);
		}
	
		
		map = new int[501][501];
		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		map[0][0] = 0;
		move();
		if (map[500][500] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(map[500][500]);			
		}

	}
	
	public static boolean isRange(int nx, int ny) {
		return 0 <= nx && nx <= 500 && 0 <= ny && ny <= 500;
	}
	
	public static boolean isDanger(int nx, int ny) {
		for (int i = 0; i < danger.length; i++) {
			if (danger[i][0] <= nx && nx <= danger[i][1] && danger[i][2] <= ny && ny <= danger[i][3]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDeath(int nx, int ny) {
		for (int i = 0; i < death.length; i++) {
			if (death[i][0] <= nx && nx <= death[i][1] && death[i][2] <= ny && ny <= death[i][3]) {
				return true;
			}
		}
		return false;
	}
	
	public static void move() {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		Deque<int[]> q = new LinkedList<>();
		q.offerFirst(new int[] {0, 0});
		while (!q.isEmpty()) {
			int[] v = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = v[0] + dx[i];
				int ny = v[1] + dy[i];
				if (isRange(nx, ny)) {
					if (isDeath(nx, ny)) continue;
					if (map[nx][ny] == Integer.MAX_VALUE) {
						if (isDanger(nx, ny)) {
							map[nx][ny] = map[v[0]][v[1]] + 1;
							q.offerLast(new int[] {nx, ny});
						} else {
							map[nx][ny] = map[v[0]][v[1]];
							q.offerFirst(new int[] {nx, ny});
						}
					}
				}
				
						
			}
			
		}
		
	}
	

}
