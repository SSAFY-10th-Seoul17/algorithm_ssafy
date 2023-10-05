import java.io.*;
import java.util.*;

/**
 * 아이디어 :
 * (1) row -> col 반복하면서 . 이 아닌 뿌요 찾기
 * (2) BFS() 반복하면서 각 뿌요가 연결된 요소개수 탐색
 * (3) 만약 (2)번 결과가 4이상이라면, 연쇄 = true and 해당 map 의 요소들 모두 to .
 * (4) col -> row 반복하면서 뿌요 떨어뜨리기
 */
public class boj11559 {
	static char[][] map = new char[12][6];
	static boolean[][] visited = new boolean[12][6];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		for(int r=0; r<12; r++) {
			String input = br.readLine();
			for(int c=0; c<6; c++) {
				map[r][c] = input.charAt(c);
			}
		}

		// solution
		boolean continuous;
		int res = 0;
		do {
			continuous = false;
			// (1) row -> col 반복하면서 . 이 아닌 뿌요 찾기
			for(int r=0; r<12; r++) {
				for(int c=0; c<6; c++) {
					if(map[r][c] == '.' || visited[r][c]) {
						continue;
					}
					// (2) BFS() 반복하면서 각 뿌요가 연결된 요소개수 탐색
					// (3) 만약 (2)번 결과가 4이상이라면, 연쇄 = true and 해당 map 의 요소들 모두 to .
					if(BFS(r, c)) {
						continuous = true;
					}
				}
			}
			// (4) col -> row 반복하면서 뿌요 떨어뜨리기
			set();
			visited = new boolean[12][6];
			res++;
		} while(continuous);

		// output
		System.out.println(res-1);
	}

	static boolean BFS(int r, int c) {
		// 탐색한 뿌요의 위치 임시 저장 -> 탐색결과, 4개이상 이어져 있다면 이 리스트 안의 좌표요소 모두 to .
		List<int[]> locations = new ArrayList<>();
		int cnt = 1;

		//BFS()
		int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { r,c });
		visited[r][c] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			locations.add(new int[] { now[0],now[1] });
			for(int d=0; d<4; d++) {
				int nr = now[0] + directions[d][0];
				int nc = now[1] + directions[d][1];
				char type = map[now[0]][now[1]];
				if(!(nr>=0 && nr<12 && nc>=0 && nc<6) || visited[nr][nc] || map[nr][nc] != type) {
					continue;
				}
				queue.offer(new int[] { nr,nc });
				visited[nr][nc] = true;
				cnt++;
			}
		}

		if(cnt >= 4) {
			pop(locations);
			return true;
		}
		return false;
	}

	static void pop(List<int[]> locations) {
		for(int[] location : locations) {
			int r = location[0], c = location[1];
			map[r][c] = '.';
		}
	}

	static void set() {
		Stack<Character> stack = new Stack<>();
		for(int c=0; c<6; c++) {
			for(int r=0; r<12; r++) {
				if(map[r][c] == '.') {
					continue;
				}
				stack.push(map[r][c]);
			}
			for(int r=11; r>=0; r--) {
				if(!stack.isEmpty()) {
					map[r][c] = stack.pop();
				} else {
					map[r][c] = '.';
				}
			}
		}
	}
}

