import java.io.*;
import java.util.*;

public class boj16197 {
	static int N, M;
	static char[][] map;
	static int[][] directions = { {0,-1},{0,1},{-1,0},{1,0} };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		int[][] starts = new int[2][2];

		for(int r=0; r<N; r++) {
			String input = br.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = input.charAt(c);
				if(map[r][c] == 'o' && starts[0][0] != 0) {
					starts[1][0] = r; starts[1][1] = c;
				} else if(map[r][c] == 'o' && starts[0][0] == 0){
					starts[0][0] = r; starts[0][1] = c;
				}
			}
		}

		// solution
		int res = BFS(starts[0][0], starts[0][1], starts[1][0], starts[1][1]);

		// output
		System.out.println(res);
	}

	static int BFS(int sr1, int sc1, int sr2, int sc2) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		queue.offer(new int[] {sr1, sc1, sr2, sc2, 0});
		visited[sr1][sc1][sr2][sc2] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now[4] >= 10) break; // 두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 하는 경우 -> -1

			for(int d=0; d<4; d++) {
				int nr1 = now[0] + directions[d][0];
				int nc1 = now[1] + directions[d][1];
				int nr2 = now[2] + directions[d][0];
				int nc2 = now[3] + directions[d][1];

				// 벽으로는 이동할 수 없음 -> 이전과 똑같은 위치에 머물기
				if(nr1>=0 && nr1<N && nc1>=0 && nc1<M && map[nr1][nc1] == '#') {
					nr1 = now[0];
					nc1 = now[1];
				}
				if(nr2>=0 && nr2<N && nc2>=0 && nc2<M && map[nr2][nc2] == '#') {
					nr2 = now[2];
					nc2 = now[3];
				}

				// 떨어지는 동전의 개수 카운팅
				int cntFall = 0;
				if(!(nr1>=0 && nr1<N && nc1>=0 && nc1<M)) cntFall++;
				if(!(nr2>=0 && nr2<N && nc2>=0 && nc2<M)) cntFall++;

				// 떨어지는 동전이 하나라면 -> 조건 만족 최단거리 반환
				if(cntFall == 1) return now[4]+1;

				// 떨어지는 동전이 0개라면 -> 큐에 넣어서 위의 동작 반복
				if(cntFall == 0 && !visited[nr1][nc1][nr2][nc2]) {
					queue.offer(new int[] {nr1, nc1, nr2, nc2, now[4]+1});
					visited[nr1][nc1][nr2][nc2] = true;
				}
			}
		}

		return -1;
	}
}
