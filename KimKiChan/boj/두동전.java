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
	private static int min;

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
		move(coins);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
	}
	
	private static void move(Position[] coins) { // bfs
		int[][] dxdy = {{-1,0}, {1,0}, {0,-1}, {0, 1}};
		min = Integer.MAX_VALUE;
		
		Deque<Position[]> dq = new ArrayDeque<>();
		dq.offer(coins);
		while(!dq.isEmpty()) {
			Position coin0 = dq.peek()[0];
			Position coin1 = dq.peek()[1];
			dq.poll();
			
			if(coin0.move >= 10 && coin1.move >= 10) {
				return;
			}
			
			for(int dir = 0; dir < 4; dir++) { // coin0
				int nx0 = coin0.x + dxdy[dir][0];
				int ny0 = coin0.y + dxdy[dir][1];
				
				int nx1 = coin1.x + dxdy[dir][0];
				int ny1 = coin1.y + dxdy[dir][1];
					
				//둘다 나가서는 안된다
				boolean out0 = outOfRange(nx0, ny0);
				boolean out1 = outOfRange(nx1, ny1);
				
				if(out0 && out1) continue; // 둘 다 밖
				else if(!out0 && !out1) { // 둘 다 안
					if(coin0.move == 9) continue;
					Position[] nextCoin = new Position[2];
					//벽
					if(board[nx0][ny0] != '#') {
						nextCoin[0] = new Position(nx0, ny0);
					}else {
						nextCoin[0] = new Position(coin0.x, coin0.y);
					}
					nextCoin[0].move = coin0.move+1;
					
					if(board[nx1][ny1] != '#') {
						nextCoin[1] = new Position(nx1, ny1);
					}else {
						nextCoin[1] = new Position(coin1.x, coin1.y);
					}
					nextCoin[1].move = coin1.move+1;
					
					dq.offer(nextCoin);
				}else { // 둘 중 하나가 나갈 때
					if(min > coin0.move+1) {
						min = coin0.move+1;
						return;
					}
				}
				
			}
			
			
		}

		return;
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
		int move;
		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.move = 0;
		}
	}
}
