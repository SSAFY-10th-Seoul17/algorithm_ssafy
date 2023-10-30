import java.io.*;
import java.util.*;

/*
 * 아이디어 :
 * 구해야하는 것 : 탈출가능여부 and 가장 빠른 탈출 시간 -> BFS
 * (1) 지훈이 이동시킨다 -> 상하좌우 BFS and 방문처리
 * (2) 불 이동시킨다 -> 상하좌우 BFS
 * (3) BFS 가 끝났을 때 가장자리에 있다면(r == 0,N-1 or c == 0,N-1) 탈출가능
 *
 * BFS :
 * - 상하좌우 { {-1,0},{1,0},{0,-1},{0,1} }
 * - map[nr][nc]의 범위확인, 방문여부확인, . 일때만 이동가능
 * - 지훈이를 한번 이동시킬때마다 -> 불을 사방으로 퍼지게 하면 안된다 => 동시에 이동한다는 조건을 만족시키기 위함
 */
public class boj4179 {
	static int R, C;
	static char[][] map;
	static int jr, jc;
	static List<int[]> fires = new ArrayList<>();
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} }; // ↑, ↓, ←, →

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == 'J') {
					jr = r; jc = c;
					map[r][c] = '.'; // 어차피 J는 이동할 수 있는 공간(문자를 통일시켜준다)
				} else if(map[r][c] == 'F') {
					fires.add(new int[] {r, c});
				}
			}
		}

		// solution
		int res = BFS();

		// output
		System.out.println(res == 0 ? "IMPOSSIBLE" : res);
	}

	static int BFS() {
		// 지훈이와 불은 동시에 이동한다 -> 큐 하나에 넣고 둘을 구분해서 처리 or 각각의 반복문을 사용
		Queue<int[]> queue = new ArrayDeque<>();
		for(int[] fire : fires) {
			queue.offer(new int[] {fire[0], fire[1]});
		}
		queue.offer(new int[] {jr, jc, 0});
		boolean[][] visited = new boolean[R][C];
		visited[jr][jc] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now.length == 3 && (now[0] == 0 || now[0] == R-1 || now[1] == 0 || now[1] == C-1)) {
				return now[2] + 1;
			}
			for(int d=0; d<4; d++) {
				int nr = now[0] + directions[d][0];
				int nc = now[1] + directions[d][1];
				if(!(nr>=0 && nr<R && nc>=0 && nc<C) || map[nr][nc] != '.') {
					continue;
				}
				// 지훈
				if(now.length == 3 && !visited[nr][nc]) {
					queue.offer(new int[] {nr, nc, now[2]+1});
					visited[nr][nc] = true;
				}
				// 불
				else {
					map[nr][nc] = 'F';
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		return 0;
	}
}

