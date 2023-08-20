import java.io.*;
import java.util.*;

public class boj3184 {
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static char[][] map;
	static boolean[][] visited;
	static int R, C, sheep, wolf;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] != '#') {;
					bfs(i,j);
				}
			}
		}
		System.out.println(sheep + " " + wolf);
	}
	
	private static void bfs(int sx, int sy) {
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sx,sy});
		visited[sx][sy] = true;
		int ts = 0, tw = 0;
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int pi = info[0];
			int pj = info[1];
			if (map[pi][pj] == 'v') {
				tw++;
			} else if (map[pi][pj] == 'o'){
				ts++;
			}
			for (int i = 0; i < 4; i++) {
				int px = pi + dirs[i][0];
				int py = pj + dirs[i][1];
				if (px >= 0 && px < R && py >= 0 && py < C && map[px][py] != '#' && !visited[px][py]) {
					visited[px][py] = true;
					q.offer(new int[] {px,py});
				}
			}
		}

		if (ts > tw) {
			sheep += ts;
		} else {
			wolf += tw;
		}
	}

}
