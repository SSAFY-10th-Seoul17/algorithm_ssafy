import java.io.*;
import java.util.*;

// 양
public class boj3184 {
	static int n, m;
	static char[][] field;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		field = new char[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				field[i][j] = line.charAt(j);
			}
		}
		
		int[] ans = new int[2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] || field[i][j] == '#') 
					continue;
				int[] result = bfs(i, j);
				ans[0] += result[0];
				ans[1] += result[1];
			}
		}
		System.out.println(ans[0] + " " + ans[1]);
	} // end of main

	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	private static int[] bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		visited[i][j] = true;
		
		int cntO = 0;
		int cntV = 0;
		if (field[i][j] == 'v')
			cntV++;
		else if (field[i][j] == 'o')
			cntO++;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int d = 0; d < 4; d++) {
				int yy = now[0] + dy[d];
				int xx = now[1] + dx[d];
				if (yy < 0 || n <= yy || xx < 0 || m <= xx)
					continue;
				if (visited[yy][xx] || field[yy][xx] == '#')
					continue;
				
				visited[yy][xx] = true;
				if (field[yy][xx] == 'v')
					cntV++;
				else if (field[yy][xx] == 'o')
					cntO++;
				q.add(new int[] {yy, xx});
			}
		}
		if (cntO > cntV)
			cntV = 0;
		else
			cntO = 0;
		return new int[] {cntO, cntV};
	}
}