package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무기공학 {
	private static int[][] dxdy = {{1,0},{0,-1},{-1,0},{0,1}};//아래 왼쪽 위 오른쪽
	private static int n;
	private static int m;
	private static boolean[][] used;
	private static int[][] tree;
	private static int maxScore;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		tree = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		used = new boolean[n][m];
		maxScore = 0;
		
		sol(0, 0, 0);
		
		System.out.println(maxScore);
		
		
	}

	private static void sol(int x, int y, int score) {
		if(x==n) {
			//최대값 찾기
			if(maxScore < score) {
				maxScore = score;
			}
			return;
		}
		
		if(!used[x][y]) {
			//4가지 부메랑 -> 다음 좌표 -> 전부 시도하면 빈칸으로
			for(int dir = 0; dir < 4; dir++) {
				int dir2 = (dir+1)%4;
				int nx1 = x + dxdy[dir][0];
				int ny1 = y + dxdy[dir][1];
				int nx2 = x + dxdy[dir2][0];
				int ny2 = y + dxdy[dir2][1];
				if(inRange(nx1, ny1) && inRange(nx2, ny2)) {
					if(!used[nx1][ny1] && !used[nx2][ny2]) {
						used[x][y] = true;
						used[nx1][ny1] = true;
						used[nx2][ny2] = true;

						if(y+1<m) {
							sol(x, y+1, score + tree[x][y]*2 + tree[nx1][ny1] + tree[nx2][ny2]);
						}else {
							sol(x+1, 0, score + tree[x][y]*2 + tree[nx1][ny1] + tree[nx2][ny2]);
						}
						
						used[nx2][ny2] = false;
						used[nx1][ny1] = false;
						used[x][y] = false;
						
					}
				}
			}
		}
		//다음좌표로
		if(y+1<m) {
			sol(x, y+1, score);
		}else {
			sol(x+1, 0, score);
		}
		
	}
	
	private static boolean inRange(int x, int y) {
		return x>=0 && y>=0 && x<n && y<m;
	}
}
