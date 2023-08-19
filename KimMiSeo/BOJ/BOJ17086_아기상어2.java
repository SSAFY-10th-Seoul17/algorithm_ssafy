// ✅ 

// 빈칸일 때 bfs를 돌려서 안전거리를 리턴받고, max값을 찾아주어야한다고 생각했습니다. 
// bfs로 퍼져나가면서 count를 저장하는 이차원배열에 거리를 저장해주면서
// 상어를 만났을 경우 이전의 좌표의 count를 리턴하게 했습니다.

import java.util.*;
import java.io.*;

public class BOJ17086_아기상어2 {
	static int n,m;
	static int[][] graph;
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	static int[][] count;
	static int[] dx = {-1,1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,-1,1,-1,1,1,-1};
	public static void main(String[] args) throws Exception {
		// n x m , 아기상어 최대 1마리 존재
		// 가장 거리가 가까운 상어와의 거리
		// 이동은 8방향 - 상하좌우 대각선
		// 안전거리가 가장 큰 칸 구하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (graph[i][j] == 0) {
					visited = new boolean[n][m];
					count = new int[n][m];
					int result = bfs(i,j);
					max = Math.max(max, result);
				}
			}
		}
		
		System.out.println(max);

	}
	
	// 각 점마다 bfs 돌면서 카운트 해주면서 1을 만나면 리턴
	private static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		count[x][y] = 1;
		
		while (!q.isEmpty()) {
			int[] polls = q.poll();
			int xx = polls[0];
			int yy = polls[1];
			
			for (int i=0; i<8; i++) {
				int nx = xx + dx[i];
				int ny = yy + dy[i];
				
				if (nx>=0 && nx<n && ny>=0 && ny<m && visited[nx][ny] == false) {
					if (graph[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.offer(new int[] {nx,ny});
						count[nx][ny] = count[xx][yy]+1; 
					}
					else {
						return count[xx][yy];
					}
				}
			}
		}
		return 0;
	}
}
