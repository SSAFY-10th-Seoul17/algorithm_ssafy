import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj6593 {

	private static char[][][] building;
	private static boolean[][][] visited;
	private static int[][][] dp;
	private static int[][] dirs = {{0,-1,0},{0,1,0},{0,0,-1},{0,0,1},{-1,0,0},{1,0,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		xx:
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken()); // 한 층 행
			int C = Integer.parseInt(st.nextToken()); // 한 층 열
			
			if (L == 0 && R == 0  && C == 0) break;
			
			building = new char[L][R][C];	// 상범빌딩
			visited = new boolean[L][R][C];
			dp = new int[L][R][C];

			int[] S = new int[3];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String s = br.readLine();
					if (s.contains("S")) {
						S[0] = i;
						S[1] = j;
						S[2] = s.indexOf('S');
					}
					building[i][j] = s.toCharArray();
				}
				br.readLine();// 각 층 사이 빈 줄 제거
			} 
	
			Queue<int[]> q = new LinkedList<>();
			q.offer(S);
			visited[S[0]][S[1]][S[2]] = true;
			dp[S[0]][S[1]][S[2]] = 0;
			
			while (!q.isEmpty()) {
				int[] now = q.poll();
				
				// 동, 서, 남, 북, 상, 하 탐색
				for (int i = 0; i < 6; i++) {
					int pi = now[0] + dirs[i][0];
					int pj = now[1] + dirs[i][1];
					int pk = now[2] + dirs[i][2];
					
					// 빌딩 범위 && 방문하지 않은 위치인 경우
					if (pi >= 0 && pi < L && pj >= 0 && pj < R && pk >= 0 && pk < C && !visited[pi][pj][pk]) {
						if (building[pi][pj][pk] == '#') {
							visited[pi][pj][pk] = true;
						}
						if (building[pi][pj][pk] == '.') {
							q.offer(new int[] {pi,pj,pk});
							dp[pi][pj][pk] = dp[now[0]][now[1]][now[2]] + 1;
							visited[pi][pj][pk] = true;
						}
						if (building[pi][pj][pk] == 'E') {
							sb.append("Escaped in ").append(dp[now[0]][now[1]][now[2]] + 1).append(" minute(s).\n");
							continue xx;
						}
					}
				}
			}
			sb.append("Trapped!\n");
		}
		System.out.println(sb.toString());
	}
	
}
