import java.util.*;
import java.io.*;
// 📝✅
public class BOJ14716_현수막 {
	static int n;
	static int m;
	static int[][] poster;
	static int cnt;
	static Queue<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		poster = new int[n][m];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				poster[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		for (int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (poster[i][j]==1) {
					bfs(i,j);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
	static int[] dx = {-1,1,0,0,1,-1,1,-1};
	static int[] dy = {0,0,-1,1,1,-1,-1,1};
	
	private static void bfs(int x, int y) {		
		queue.offer(new int[] {x, y});

		while (!queue.isEmpty()) {
			int[] polls = queue.poll();
			int xx = polls[0];
			int yy = polls[1];
//			poster[xx][yy] = 0; 
			
			for (int i=0; i<8; i++) {
				int nx = xx + dx[i];
				int ny = yy + dy[i];
				
				if (nx>=0 && nx<n && ny>=0 && ny<m && poster[nx][ny] == 1) {
					poster[nx][ny] = 0;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
