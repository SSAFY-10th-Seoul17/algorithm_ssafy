import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, sheep, wolf;
	static char[][] map; 
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());  // 행
		C = Integer.parseInt(st.nextToken());  // 열
		
		map = new char[R][C];
		visited = new boolean[R][C];  // true: 울타리 or 이미 확인, false: 양 or 늑대 or 땅
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = line.charAt(j);
				map[i][j] = ch;
				if (ch == '#') {
					visited[i][j] = true;  // 울타리 표시
				}
			}
		}
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(sheep + " " + wolf);
		
	}
	
	public static boolean inRange(int i, int j) {
		return 0 <= i && i < R && 0 <= j && j < C;
	}
	
	public static void bfs(int i, int j) { 
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int s = 0;  // 영역 내 양 수
		int w = 0;  // 영역 내 늑대 수
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		// 시작 위치에 늑대 혹은 양이 있는지 확인
		if (map[i][j] == 'o') {
			s++;
		} else if (map[i][j] == 'v') {
			w++;
		}
		
		while (!q.isEmpty()) {
			int[] v = q.poll();
			for (int k = 0; k < 4; k++) {
				int nx = v[0] + dx[k];
				int ny = v[1] + dy[k];
				if (inRange(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] == 'o') {
						s++;
					} else if (map[nx][ny] == 'v') {
						w++;
					}
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		if (s <= w) {
			// 늑대가 양보다 많은 경우
			wolf += w;
		} else {
			// 양이 늑대보다 많은 경우
			sheep += s;
		}
	}
	

}
