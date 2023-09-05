import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17086 {
	static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	static boolean[][] visited;
	private static int[][] map, dist;
	private static int N, M, res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					visited = new boolean[N][M];
					bfs(i,j);
				}
			}
		}
		System.out.println(res);
	}
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		dist = new int[N][M];
		
		xx:
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 8; i++) {
				int px = now[0] + dirs[i][0];
				int py = now[1] + dirs[i][1];
				if (px >= 0 && px < N && py >= 0 && py < M && !visited[px][py]) {
					dist[px][py] = dist[now[0]][now[1]] + 1;
					if (map[px][py] == 0) {
						q.offer(new int[] {px,py});
						visited[px][py] = true;
					} else {
						res = Math.max(res, dist[px][py]);
						break xx;
					}
				}
			}
		}
		
	}

}
