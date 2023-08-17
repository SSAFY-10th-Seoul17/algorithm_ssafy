import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj5427 {
	private static int w;
	private static int h;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static char[][] arr;
	private static int[] curPos;
	private static int[][] run;
	private static Deque<int[]> newFire = new ArrayDeque<int[]>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			arr = new char[h][w];
			curPos = new int[2];; // 상근이 위치
			
			for(int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					arr[i][j] = str.charAt(j);
					if(arr[i][j] == '@') { // 상근이의 현재 위치
						curPos[0] = i;
						curPos[1] = j;
					}
					if(arr[i][j] == '*') {
						newFire.offer(new int[] {i, j});
					}
				}
			}
			run = new int[h][w];

			if(bfs()) {
				sb.append(run[curPos[0]][curPos[1]]).append("\n");
			}else {
				sb.append("IMPOSSIBLE\n");
			}
			newFire.clear();
		}// end of test case
		System.out.println(sb.toString());
	}// end of main

	private static boolean bfs() {
		Deque<int[]> sg = new ArrayDeque<int[]>(); // 상근의 위치정보
		sg.offer(curPos);
		int[] current = new int[2];
		run[curPos[0]][curPos[1]] = 1;
		
		while(!sg.isEmpty()) {
			int size = sg.size();
			while(--size>=0) {
				current = sg.poll();
				if(arr[current[0]][current[1]] == '*') {
					continue;
				}
				// 상하좌우 이동
				for(int i = 0; i < 4; i++) {
					int nx = current[0] + dx[i];
					int ny = current[1] + dy[i];
					// 배열 안
					if(nx>=0 && nx<h && ny>=0 && ny<w) {
						// 벽, 불
						if(arr[nx][ny] == '#' || arr[nx][ny] == '*') continue;
						if(arr[nx][ny] == '.' && run[nx][ny]==0) {
							sg.offer(new int[] {nx, ny});
							//이동
							arr[nx][ny] = '@';
							run[nx][ny] = run[current[0]][current[1]] + 1;
						}
					}else { // 탈출 완료
						curPos[0] = current[0];
						curPos[1] = current[1];
						return true;
					}	
				}
				arr[current[0]][current[1]] = '.';
			}
			fire();
			
		}// queue empty

		return false;
	}// end of bfs
	
	private static void fire() {
		//모든 불이 아니라 던질 불을 체크
		
		int size = newFire.size();
		for(int i = 0; i < size; i++) {// 상하좌우 불이 퍼진다
			int[] firePos = newFire.poll();
			for(int k = 0; k < 4; k++) {
				// 불의 다음 예상 위치
				int nx = firePos[0] + dx[k];
				int ny = firePos[1] + dy[k];
				// 배열 안
				if(nx>=0 && nx<h && ny>=0 && ny<w) {
					// 벽, 불
					if(arr[nx][ny] == '#' || arr[nx][ny] == '*') continue;
					
					arr[nx][ny] = '*';
					newFire.offer(new int[] {nx, ny});
					
				}
			}
		}	
	}// end of fire
	
}// end of class
