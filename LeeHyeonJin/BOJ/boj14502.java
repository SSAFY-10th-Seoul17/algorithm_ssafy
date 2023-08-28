import java.io.*;
import java.util.*;

public class boj14502 {
	static int n;
	static int m;
	static int[][] map;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		// (1) 벽을 세울 수 있는 조합을 우선 구함 : constructWall()
		// (2) 벽을 세웠을 때 각각 dfs로 바이러스가 퍼져나가는 것을 확인 후, max 안전영역 갱신 : spreadVirus()
		constructWall(0);

		// output
		bw.write(max+"");
		bw.flush();
		bw.close();
	}

	static void constructWall(int cnt) {
		if(cnt == 3) {
			spreadVirus();
			return;
		}
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(map[r][c] == 0) {
					map[r][c] = 1;
					constructWall(cnt+1);
					map[r][c] = 0;
				}
			}
		}
	}

	static void spreadVirus() {
		int[][] tempMap = new int[n][m];
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				tempMap[r][c] = map[r][c];
			}
		}

		Stack<int[]> stack = new Stack<>();
		boolean[][] visited = new boolean[n][m];
		int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} }; // U D L R

		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(tempMap[r][c] == 2) {
					stack.push(new int[] {r, c});
					visited[r][c] = true;
					while(!stack.isEmpty()) {
						int[] s = stack.pop();
						int sr = s[0];
						int sc = s[1];
						tempMap[sr][sc] = 2;
						for(int d=0; d<4; d++) {
							int nr = sr+directions[d][0];
							int nc = sc+directions[d][1];
							if(nr>=0 && nr<n && nc>=0 && nc<m) {
								if(!visited[nr][nc] && tempMap[nr][nc] == 0) {
									stack.push(new int[] {nr,nc});
									visited[nr][nc] = true;
								}
							}
						}
					}
				}
			}
		}

		int cnt = 0;
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(tempMap[r][c] == 0) {
					cnt++;
				}
			}
		}

		max = Math.max(max, cnt);
	}
}
