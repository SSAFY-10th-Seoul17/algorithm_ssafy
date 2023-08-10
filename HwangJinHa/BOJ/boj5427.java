import java.io.*;
import java.util.*;

class loc {
	int y;
	int x;
	public loc(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	
	static ArrayList<loc> fire_start = new ArrayList<loc>();
	static loc sangun;
	
	static char[][] cells;
	static int[][] fire;
	static int[][] move;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static void bfs() {
		Queue<loc> q = new LinkedList<loc>();
		for(int i = 0; i < fire_start.size(); i++) {
			loc tmp = fire_start.get(i);
			q.add(tmp);
			fire[tmp.y][tmp.x] = 0; 
		}
		while(!q.isEmpty()) {
			loc now = q.poll();
			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				
				if (y < 0 || n <= y || x < 0 || m <= x) 
					continue;
				
				if(cells[y][x] != '#' && fire[y][x] == -1) {
					q.offer(new loc(y, x));
					fire[y][x] = fire[now.y][now.x] + 1;
				}
			}
		}
		
		q.clear();
		q.add(sangun);
		move[sangun.y][sangun.x] = 0;
		while(!q.isEmpty()) {
			loc now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];

				if (y < 0 || n <= y || x < 0 || m <= x) {
					sb.append(move[now.y][now.x] + 1 + "\n");
//					System.out.println(move[now.y][now.x]+ 1);
					return;
				}
				
				if (cells[y][x] == '.' && move[y][x] == -1) {
					if (fire[y][x] > move[now.y][now.x]+ 1 || fire[y][x] == -1) {
						q.add(new loc(y, x));
						move[y][x] = move[now.y][now.x] + 1; 
					}
				}
			}
		}
//		System.out.println("IMPOSSIBLE\n");
		sb.append("IMPOSSIBLE\n");
	}
	
	public static void main(String[] args) throws IOException {
		int tc = Integer.valueOf(br.readLine());
		while(tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			m = Integer.valueOf(st.nextToken());
			n = Integer.valueOf(st.nextToken());
			
			fire_start.clear();

			fire = new int[n][m];
			move = new int[n][m];

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					fire[i][j] = -1;
					move[i][j] = -1;
				}
			}

			cells = new char[n][m];
			for(int i = 0; i < n; i++) {
				String line = br.readLine();
				for(int j = 0; j < m; j++) {
					cells[i][j] = line.charAt(j);
					if (cells[i][j] == '@')
						sangun = new loc(i, j);
					else if(cells[i][j] == '*')
						fire_start.add(new loc(i, j));
				}
			}
			
			bfs();
		}
		System.out.println(sb);
	}
}
