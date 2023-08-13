import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static Queue<int[]> q;
	
	public static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
	
	public static void bfs() {
		int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		while (!q.isEmpty()) {
			int[] w = q.poll();
			for (int i = 0; i < 8; i++) {
				int x = w[0] + dx[i];
				int y = w[1] + dy[i];
				if (inRange(x, y) && map[x][y] == 0) {
					map[x][y] = map[w[0]][w[1]] + 1;
					q.offer(new int[] {x, y});
				}
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // 행
		M = Integer.parseInt(st.nextToken());  // 열
		
		map = new int[N][M];  // 배열
		q = new LinkedList<int[]>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int token = Integer.parseInt(st.nextToken());
				if (token == 1) {
					q.offer(new int[] {i, j});
				}
				map[i][j] = token; 
			}
		}
		
		
		bfs();
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		
		System.out.println(max-1);
		
	}  // end of main
}  // end of class
