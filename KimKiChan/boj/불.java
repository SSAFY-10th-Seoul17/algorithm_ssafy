package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

//4179번
public class 불 {
	private static int r;
	private static int c;
	private static char[][] maze;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		maze = new char[r][c];
		int[] jihoon = new int[2];
		ArrayList<int[]> fire = new ArrayList<>();
		
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				maze[i][j] = str.charAt(j);
				if(maze[i][j] == 'J') {
					jihoon[0] = i;
					jihoon[1] = j;
				}else if(maze[i][j] == 'F') {
					fire.add(new int[] {i, j});
				}
			}
		}

		escape(jihoon, fire);
		
	}

	private static void escape(int[] jihoon, ArrayList<int[]> fire) {
		boolean escaped = false; // 탈출 여부
		int[][] dxdy = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
		int[][] time = new int[r][c]; // 탈출 시간
		time[jihoon[0]][jihoon[1]] = 1;
		
		Deque<int[]> player = new ArrayDeque<>();
		Deque<int[]> enemy = new ArrayDeque<>();
		
		player.add(jihoon);
		for(int i = 0; i < fire.size(); i++) {
			enemy.add(fire.get(i));
		}
		
		
		while(!player.isEmpty()) {
			int sizePlayer = player.size();
			while(sizePlayer-- > 0) {
				int x = player.peek()[0];
				int y = player.peek()[1];
				player.poll();
				
				if(maze[x][y] == 'F') continue;
				
				for(int i = 0; i < 4; i++) { // 상하좌우
					int nx = x + dxdy[i][0];
					int ny = y + dxdy[i][1];
					
					if(nx >= 0 && ny >= 0 && nx < r && ny < c) { // 미로 안
						if(maze[nx][ny] == '.' && time[nx][ny] == 0) {// 지나갈 수 있는 지나간 적이 없는 공간
							time[nx][ny] = time[x][y] + 1; // 시간 갱신
							maze[nx][ny] = 'J'; // 이동
							player.add(new int[] {nx, ny});
						}
						
					}else {// 미로 밖
						escaped = true;
						System.out.println(time[x][y]);
						break;
					}
					
				}
				if(escaped) break;
			}
			
			if(escaped) break;
			
			int sizeEnemy = enemy.size();
			
			while(sizeEnemy-- > 0) {
				int fireX = enemy.peek()[0];
				int fireY = enemy.peek()[1];
				enemy.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx = fireX + dxdy[i][0];
					int ny = fireY + dxdy[i][1];
					
					if(nx >= 0 && ny >= 0 && nx < r && ny < c) { // 미로 안
						if(maze[nx][ny] == '.' || maze[nx][ny] == 'J') {
							maze[nx][ny] = 'F'; // 불 확산
							enemy.add(new int[] {nx, ny});
						}
					}
				}
			}
			
			
		}
		
		if(!escaped) {
			System.out.println("IMPOSSIBLE");
		}
		
	}
}

