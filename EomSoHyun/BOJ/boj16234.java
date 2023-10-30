import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, R, sum, cnt;
	static int[][] A;
	static boolean[][] visited;
	static Queue<int[]> pos;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // 땅 크기
		L = Integer.parseInt(st.nextToken());  // L명 이상
		R = Integer.parseInt(st.nextToken());  // R명 이하
		
		A = new int[N][N];  // 각 나라 인구 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		
		int rst = 0;
		pos = new LinkedList<int[]>();
		while (true) {
			boolean flag = true;
			visited = new boolean[N][N];  // 방문 표시
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						sum = 0;
						cnt = 0;
						dfs(i, j);
						if (cnt > 1) {
							flag = false;
							while (!pos.isEmpty()) {
								int[] xy = pos.poll();
								A[xy[0]][xy[1]] = sum / cnt;
							}
						} else {
							visited[i][j] = false;
							pos.clear();
						}
					}
				}
			}
			
			if (flag) break;
			rst++;	
		}
		
		System.out.println(rst);

	}
	
	public static void dfs(int x, int y) {
		pos.offer(new int[] {x, y});
		visited[x][y] = true;
		sum += A[x][y];
		cnt++;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (L <= Math.abs(A[x][y] - A[nx][ny]) && Math.abs(A[x][y] - A[nx][ny]) <= R && !visited[nx][ny]) {
					dfs(nx, ny);
				}
			}
			
		}
	}

}
