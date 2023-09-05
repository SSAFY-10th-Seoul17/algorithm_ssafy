import java.io.*;
import java.util.*;

public class boj3184 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		int cntWolf = 0; int cntSheep = 0;

		for(int r=0; r<R; r++) {
			String input = br.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = input.charAt(c);
				if(map[r][c] == 'v') cntWolf++;
				else if(map[r][c] == 'o') cntSheep++;
			}
		}

		// solution
		int[] subInfo = dfs(map, R, C);
		int survWolf = cntWolf - subInfo[0];
		int survSheep = cntSheep - subInfo[1];

		// output
		System.out.println(survSheep+" "+survWolf);
	}

	/* 아이디어 : 빈필드가 이어진 공간마다 모두 탐색하며(dfs) 늑대수 vs 양의수 확인 */
	static int[] dfs(char[][] map, int R, int C) {
		int subWolf = 0; int subSheep = 0;
		int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
		Stack<int[]> stack = new Stack<>();
		boolean[][] visited = new boolean[R][C];

		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] != 'v' && map[r][c] != 'o') continue;
				stack.push(new int[] {r, c});
				visited[r][c] = true;
				int tempWolf = map[r][c] == 'v' ? 1 : 0;
				int tempSheep = map[r][c] == 'o' ? 1 : 0;

				while(!stack.isEmpty()) {
					int[] now = stack.pop();
					for(int d=0; d<4; d++) {
						int nr = now[0] + directions[d][0];
						int nc = now[1] + directions[d][1];
						if(!(nr>=0 && nr<R && nc>=0 && nc<C) || visited[nr][nc] || map[nr][nc] == '#') continue;
						stack.push(new int[] {nr, nc});
						visited[nr][nc] = true;
						if(map[nr][nc] == 'v') tempWolf++;
						else if(map[nr][nc] == 'o') tempSheep++;
					}
				}

				if(tempSheep > tempWolf) subWolf += tempWolf;
				else subSheep += tempSheep;
			}
		}

		return new int[] {subWolf, subSheep};
	}
}
