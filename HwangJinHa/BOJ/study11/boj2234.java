package study8_5;

import java.io.*;
import java.util.*;

// 성곽 
public class boj2234 {
	static final int[] dy = {0, -1, 0, 1};
	static final int[] dx = {-1, 0, 1, 0};
	
	static int n, m;
	static int[][] cells;
	static int[][] cellId;
	static boolean[][] visited;
	static int max1;
	static int max2;
	
	// 방 크기 저장 
	static ArrayList<Integer> roomCapacity = new ArrayList<Integer>();
	// 방향이 있는 그래프로 만들어도 모두 검사하기 때문에 상관없다 
	static ArrayList<Set<Integer>> graph = new ArrayList<Set<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.valueOf(st.nextToken());
		n = Integer.valueOf(st.nextToken());
		
		cells = new int[n][m];
		cellId = new int[n][m];
		visited = new boolean[n][m];
		max1 = 0;
		max2 = 0;
		roomCapacity.add(0);
		graph.add(new HashSet<Integer>());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				cells[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		int roomId = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					roomIdentify(i, j, roomId++);
				}
			}
		}
		
		for (int i = 1; i < graph.size(); i++) {
			Set<Integer> conn = graph.get(i);
			for (int next : conn) {
				max2 = Math.max(max2, roomCapacity.get(i) + roomCapacity.get(next));
			}
		}
		
		System.out.println(roomId - 1);
		System.out.println(max1);
		System.out.println(max2);
		
		
	} // end of main

	private static void roomIdentify(int y, int x, int roomId) {
		Set<Integer> conn = new HashSet<>();
		Queue<Loc> q = new LinkedList<>();

		visited[y][x] = true;
		q.add(new Loc(y, x));
		cellId[y][x] = roomId;

		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			Loc now = q.poll();
			for (int i = 0; i < n; i++) {
				int yy = now.y + dy[i];
				int xx = now.x + dx[i];
				// 범위 밖이라면 검사x
				if (yy < 0 || n <= yy || xx < 0 || m <= xx)
					continue;

				// 범위 안이지만 막혀있다면 
				if ((cells[now.y][now.x] & (1 << i)) != 0) {
					conn.add(cellId[yy][xx]);
					continue;
				}
				if (visited[yy][xx])
					continue;

				// 범위 안이고 막혀있지 않다.
				visited[yy][xx] = true;
				q.add(new Loc(yy, xx));
				cellId[yy][xx] = roomId;
			}
		}
		
		conn.remove(0);
		conn.remove(roomId);
		graph.add(conn);
		roomCapacity.add(cnt);
		max1 = Math.max(max1, cnt);
	}
	
	static class Loc {
		int y;
		int x;
		Loc() {}
		Loc(int y, int x) {this.y = y; this.x = x;}
	}
} // end of class
