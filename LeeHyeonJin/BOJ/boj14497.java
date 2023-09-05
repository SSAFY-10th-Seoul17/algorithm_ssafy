import java.io.*;
import java.util.*;

public class boj14497 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = { {0,1},{1,0},{0,-1},{-1,0} }; //동 남 서 북
	static int[][] walls = { {1,3,5,9,7,11,13,15},{2,3,6,10,7,11,14,15},{4,5,6,12,7,13,14,15},{8,9,10,12,11,13,14,15} }; //서 북 동 남
	static int maxTempSize = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		int cntRoom = 0;
		int maxSize = Integer.MIN_VALUE;
		visited = new boolean[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!visited[r][c]) {
					maxSize = Math.max(maxSize, BFS(r, c));
					cntRoom++;
				}
			}
		}
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				for(int d=0; d<4; d++) {
					for(int wall : walls[d]) {
						if(map[r][c] == wall) {
							map[r][c] -= 1 << d;
							startTemp();
							map[r][c] += 1 << d;
						}
					}
				}
			}
		}

		// output
		sb.append(cntRoom).append("\n").append(maxSize).append("\n").append(maxTempSize);
		System.out.println(sb);
	}

	static void startTemp() {
		visited = new boolean[N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!visited[r][c]) {
					maxTempSize = Math.max(maxTempSize, BFS(r, c));
				}
			}
		}
	}

	static int BFS(int r, int c) {
		int cntSize = 0;

		Queue<int[]> queue = new ArrayDeque<>();
		cntSize++;
		queue.offer(new int[] { r, c });
		visited[r][c] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = now[0] + directions[d][0];
				int nc = now[1] + directions[d][1];
				if(!(nr>=0 && nr<N && nc>=0 && nc<M) || visited[nr][nc]) continue;

				boolean possible = true;
				for(int wall : walls[d]) {
					if(map[nr][nc] == wall) possible = false;
				}

				if(possible) {
					cntSize++;
					queue.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
		}

		return cntSize;
	}
}
