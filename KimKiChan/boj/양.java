import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/** 백준 3184번 */
public class 양 {

	private static int r;
	private static int c;
	private static char[][] garden;
	private static boolean[][] isSearched;
	private static int sheep;
	private static int wolf;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 행 r
		r = Integer.parseInt(st.nextToken());
		// 열 c
		c = Integer.parseInt(st.nextToken());
		// 뒷마당
		garden = new char[r][c];
		
		for(int i = 0; i < r; i++) {
			String str = br.readLine();
			for(int j = 0; j < c; j++) {
				garden[i][j] = str.charAt(j);
			}
		}
		
		// 양 > 늑대 : 양의 승리 늑대는 쫓겨난다, else : 양이 먹힌다
		// 같은 영역 = 수평, 수직으로 이동하여 울타릴 지나지 않고 다른 칸으로 이동 가능
		// 배열 내부를 탐색 -> 울타리 내부 탐색 -> 양과  늑대의 수 찾기
		isSearched = new boolean[r][c];
		int totalSheep = 0;
		int totalWolf = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(isSearched[i][j]) continue; // 이미 탐색했을 경우
				if(garden[i][j] == 'o' || garden[i][j] == 'v') { // 양이나 늑대는 울타리 안에 존재
					bfs(i, j);
					totalSheep += sheep;
					totalWolf += wolf;
				}
			}
		}

		System.out.println(totalSheep + " " + totalWolf);
		
	}// end of main

	private static void bfs(int x, int y) {

		Deque <int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {x, y});
		isSearched[x][y] = true;
		
		sheep = 0;
		wolf = 0;
		if(garden[x][y] == 'o') sheep = 1;
		else if(garden[x][y] == 'v') wolf = 1;
		
		while(!dq.isEmpty()) {
			int currentX = dq.peek()[0];
			int currentY = dq.peek()[1];
			dq.pop();
			
			for(int i = 0; i < 4; i++) { // 상하좌우
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				
				if(nextX >= 0 && nextX < r && nextY >= 0 && nextY < c) {// 배열 안
					// 탐색한 적이 있는 경우
					if(isSearched[nextX][nextY]) continue;
					// 울타리를 넘을 경우
					if(garden[nextX][nextY] == '#') continue;
					
					dq.offer(new int[] {nextX, nextY});
					isSearched[nextX][nextY] = true;
					
					if(garden[nextX][nextY] == 'o') {//양일 경우
						sheep++;
					}else if(garden[nextX][nextY] == 'v') {//늑대일 경우
						wolf++;
					}
				}
			}
		}
		
		if(sheep > wolf) { // 양이 늑대보다 많으면
			wolf = 0;
		}else if(wolf >= sheep) { // 늑대가 양보다 많거나 같으면
			sheep = 0;
		}
	}

}
