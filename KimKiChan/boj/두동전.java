package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//16197번
public class 두동전 {
	private static int n;
	private static int m;
	private static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		Position[] coins = new Position[2];
		int idx = 0;
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j] == 'o') {
					coins[idx++] = new Position(i, j);
				}
			}
		}
		br.close(); // 입력 종료
		
		// 두 동전 중 하나만 보드에서 떨어뜨리기
		int min = move(coins);
		
		System.out.println(min);
		
	}
	
	private static int move(Position[] coins) { // bfs
		int[][] dxdy = {{-1,0}, {1,0}, {0,-1}, {0, 1}};
		int move = 1;
		
		boolean[][][][] visited = new boolean[n][m][n][m];
		visited[coins[0].x][coins[0].y][coins[1].x][coins[1].y] = true;
		
		Deque<Position[]> dq = new ArrayDeque<>();
		dq.offer(coins);

		while(!dq.isEmpty()) {
			int size = dq.size();
			while(size-- > 0) {
				Position coin0 = dq.peek()[0];
				Position coin1 = dq.peek()[1];
				dq.poll();
				
				for(int dir = 0; dir < 4; dir++) {
					int nx0 = coin0.x + dxdy[dir][0];
					int ny0 = coin0.y + dxdy[dir][1];
					
					int nx1 = coin1.x + dxdy[dir][0];
					int ny1 = coin1.y + dxdy[dir][1];
					
					//둘다 나가서는 안된다
					boolean out0 = outOfRange(nx0, ny0);
					boolean out1 = outOfRange(nx1, ny1);
					
					if(out0 && out1) continue; // 둘 다 밖
					else if(!out0 && !out1) { // 둘 다 안
						Position[] nextCoin = new Position[2];
						//벽
						if(board[nx0][ny0] == '#') { // 제자리
							nx0 = coin0.x;
							ny0 = coin0.y;
						}
						nextCoin[0] = new Position(nx0, ny0);
						
						if(board[nx1][ny1] == '#') { // 제자리
							nx1 = coin1.x;
							ny1 = coin1.y;
						}
						nextCoin[1] = new Position(nx1, ny1);

						if(visited[nx0][ny0][nx1][ny1]) continue;
						dq.offer(nextCoin);
						
						visited[nx0][ny0][nx1][ny1] = true;
					}else {
						return move;
					}
				}
			
			}
			move++;
			if(move > 10) {
				break;
			}

		}
		
		return -1;
	}
	
	private static boolean outOfRange(int x, int y) {
		if(x >= 0 && y >= 0 && x < n && y < m) {
			return false;
		}
		return true;
	}

	private static class Position{
		int x;
		int y;
		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
