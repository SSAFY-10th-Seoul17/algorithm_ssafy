package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 소가길을건너간이유6 {
	private static int n;
	private static HashSet<String> roads;
	private static ArrayList<ArrayList<Integer>> cows;
	private static int r;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		//길이 아니라 장벽
		//등록된 길이 없으면 지나갈 수 있다.
		
		roads = new HashSet<>(); // 도로
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			StringBuilder road1 = new StringBuilder();
			road1.append(x1).append(" ").append(y1).append(" ").append(x2).append(" ").append(y2);
			roads.add(road1.toString());
			
			StringBuilder road2 = new StringBuilder();
			road2.append(x2).append(" ").append(y2).append(" ").append(x1).append(" ").append(y1);
			roads.add(road2.toString());
		}
		
		cows = new ArrayList<>(); // 소
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> cow = new ArrayList<>();
			cow.add(x);
			cow.add(y);
			cows.add(cow);
		}
		
		int count = 0;
		
		for(ArrayList<Integer> cow : cows) {
			count += visit(cow.get(0), cow.get(1));
		}
		
		System.out.println(count/2);
		
	}
	
	private static int visit(int x, int y) {
		//방향
		int[][] dxdy = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		//방문처리
		boolean[][] visited = new boolean[n+1][n+1];
		visited[x][y] = true; // 시작위치
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {x, y});
		
		//길을 건너지 않고 갈 수 있는 모든 구역 탐색
		while(!dq.isEmpty()) {
			int[] pos = dq.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = pos[0] + dxdy[dir][0];
				int ny = pos[1] + dxdy[dir][1];
				
				if(nx >= 1 && ny >= 1 && nx <= n && ny <= n) {
					//방문 확인
					if(visited[nx][ny]) continue;
					//길 확인
					StringBuilder road = new StringBuilder();
					road.append(pos[0]).append(" ").append(pos[1]).append(" ").append(nx).append(" ").append(ny);
					//길이 있으면 우회
					if(roads.contains(road.toString())) continue;
					//방문
					visited[nx][ny] = true;
					dq.offer(new int[] {nx, ny});
				}
			}
		}
		
		//가지 못한 곳에 소가 있는지 확인
		int count = 0;
		for(ArrayList<Integer> cow : cows) {
			int tempX = cow.get(0);
			int tempY = cow.get(1);
			//자신 제외
			if(tempX == x && tempY == y) continue;
			//방문하지 못한 곳에 소가 존재하면
			if(!visited[tempX][tempY]) {
				count++;
			}
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= n; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		return count;
	}
	
}
