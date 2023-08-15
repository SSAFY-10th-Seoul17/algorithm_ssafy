import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int m, n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
	static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
	
	public static void calc(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		while (!q.isEmpty()) {
			int[] node = q.poll();
			for (int k = 0; k < 8; k++) {
				int nx = node[0] + dx[k];
				int ny = node[1] + dy[k];
				if (0 <= nx && nx < m && 0 <= ny && ny < n) {  // 범위 벗어나는지 확인
					if (!visited[nx][ny] && map[nx][ny] != 0) {
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
		
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());  // 행
		n = Integer.parseInt(st.nextToken());  // 열
		visited = new boolean[m][n];
		map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					calc(i, j);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
		
	}

}
