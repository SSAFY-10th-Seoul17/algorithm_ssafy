import java.util.*;
import java.io.*;
// 06:28 - 06:50

// (0,0)부터 bfs를 돌면서 양과 늑대의 수를 계산해주면 된다고 생각했습니다.
// visited으로 방문처리를 해주어서 하나의 영역을 돌고 리턴되면 다음 방문하지 않은 시작점부터 다시 bfs를 돌게 했습니다.
// bfs안에서는 늑대, 양 개수를 세고, 리턴되기 직전에 더 많은 걸 전역변수에 계속 더해주었습니다.  

public class BOJ3184_양 {
	static int R,C,totalSheep, totalWolf;
	static char[][] graph;
	public static void main(String[] args) throws Exception {
		// . : 빈필드, # : 울타리 , o : 양 , v: 늑대
		// 상하좌우로만, 울타리를 지나지 않고 이동가능하면 같은 영역이다.
		// 영역 안에 양의 수 > 늑대의 수 : 이기고, 늑대를 쫓아냄. 그렇지 않으면 늑대는 지역 모든 양을 먹음
		// 살아남은 양과 늑대의 수 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph= new char[R][C];

		for (int i=0; i<R; i++) {
			String in = br.readLine();
			for (int j=0; j<C; j++) {
				graph[i][j] = in.charAt(j);
			}
		}
		
		visited = new boolean[R][C];
		// 영역 별로 -> bfs , 0,0 부터 시작하면 리턴될 것임 , 포문돌려서 
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (!visited[i][j]) {
					bfs(i,j);
				}
			}
		}
		
		sb.append(totalSheep).append(" ").append(totalWolf);
		System.out.println(sb.toString());
	}
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
 	private static void bfs(int x, int y) {
 		int sheep =0, wolf = 0;
		Queue<int []> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] polls = q.poll();
			int curx = polls[0];
			int cury = polls[1];
			
			for (int i=0; i<4; i++) {
				int nx = curx + dx[i];
				int ny = cury + dy[i];
				
				// 늑대, 양 개수를 세서 더 많은 걸 살리고 적은건 0으로 
				if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && graph[nx][ny]!='#') { // 범위 안에있고 , 방문하지 않았고, 울타리가 아니면
					if (graph[nx][ny] == 'v') { // 늑대
						wolf++;
					}
					else if (graph[nx][ny] == 'o') { // 양
						sheep++;
					}
					
					visited[nx][ny] = true;
					q.offer(new int[] {nx,ny});
				}
			}
		}
		if (wolf < sheep) {
			totalSheep += sheep;
		}
		else {
			totalWolf += wolf;
		}
	}
}
