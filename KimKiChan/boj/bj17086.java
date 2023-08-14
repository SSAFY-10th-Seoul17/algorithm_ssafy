import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj17086 {
	// NxM 공간, 1x1 공간 하나에 아기 상어 최대 1마리
	// 안전 거리 : 가장 거리가 가까운 아기 상어와의 거리
	// 거리 : 지나야 하는 칸의 수, 이동 : 8방향
	// 출력 : 안전 거리의 최댓값
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m]; // 입력 배열
		Deque<int[]> q = new ArrayDeque<>(); // 위치 정보
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) { // 상어일 경우
					q.offer(new int[] {i, j});
				}
			}
		}
		// 이동 방향
		int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
		int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
		
        // 배열을 안전거리로 채우기
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();
			// 이동
			for(int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 배열 밖
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				// 빈칸 채우기
				if(arr[nx][ny] == 0) {
					arr[nx][ny] = arr[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
				// 안전거리 갱신 ( 가장 가까운 아기상어와의 거리 )
				if(arr[nx][ny] > arr[x][y] + 1) {
					arr[nx][ny] = arr[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}	
			}
		} // end of bfs
		
		int maxDist = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] > maxDist) {
					maxDist = arr[i][j];
				}
			}
		}
		
		System.out.println(maxDist - 1);
		
	} // end of main

} // end of class
