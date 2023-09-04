import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj1584 {
	private static boolean[][] visited;
	private static int[][] map;
	private static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[501][501];
		visited = new boolean[501][501];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
				for (int k = Math.min(x1, x2); k <= Math.max(x1,x2); k++) {
					map[j][k] = 1;	// 위험구역 
				}
			}
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
				for (int k = Math.min(x1, x2); k <= Math.max(x1, x2); k++) {
					map[j][k] = 2;	// 죽음구역
				}
			}
		}
		int ans = -1;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[0][0] = true;
		q.offer(new int[] {0,0,0});
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int x = info[0];
			int y = info[1];
			if (x == 500 && y == 500) {
				ans = info[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int px = x + dirs[i][0];
				int py = y + dirs[i][1];
				if (px < 0 || px > 500 || py < 0 || py > 500 || visited[px][py]) continue;
				visited[px][py] = true;
				if (map[px][py] == 1) {
					visited[px][py] = true;
					q.offerLast(new int[] {px,py,info[2]+1});
				} else if (map[px][py] == 0) {
					visited[px][py] = true;
					q.offerFirst(new int[] {px,py,info[2]});
				}
			}
		}
		System.out.println(ans);
    }
}
