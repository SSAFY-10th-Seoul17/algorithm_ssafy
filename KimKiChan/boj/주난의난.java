import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

//14497번
public class 주난의난 {
	private static int n;
	private static int m;
	private static int[][] dxdy = {{-1, 0},{1, 0},{0, -1},{0, 1}}; // 상하좌우
	private static int[] joonan;
	private static int[] culprit;
	private static char[][] classroom;
	private static int[][] jumped;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		joonan = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		culprit = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		
		classroom = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				classroom[i][j] = str.charAt(j);
			}
		}// 입력 끝
		
		//파동
		wave();
		
	}// end of main

	private static void wave() { // bfs
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {joonan[0], joonan[1]});
		
		jumped = new int[n][m];
		jumped[joonan[0]][joonan[1]] = 1;
		
		while(!dq.isEmpty()) {
			int[] current = dq.pollFirst();
			
			int x = current[0];
			int y = current[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dxdy[i][0];
				int ny = y + dxdy[i][1];
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue; // 범위 밖
				if(jumped[nx][ny] > 0) continue; // 이미 지나간 자리
				
				if(classroom[nx][ny] == '0') { // 0은 만나도 통과된다
					jumped[nx][ny] = jumped[x][y]; // 같은 점프 횟수
					dq.offerFirst(new int[] {nx, ny}); // 1로 둘러쌓인 울타리 안에서 배열값 0의 jump값을 우선적으로 입력
				}else if(classroom[nx][ny] == '1') { // 쓰러트린다
					jumped[nx][ny] = jumped[x][y] + 1; // 울타리 너머는 한번 더 점프를 해야 파동이 퍼진다
					dq.offerLast(new int[] {nx, ny}); // 배열값 0들의 위치를 우선적으로 poll할 수 있도록 뒤에 입력한다
				}else if(classroom[nx][ny] == '#') {// 범인과 조우
					System.out.println(jumped[x][y]); // 출력
					return;
				}
			}
			
		}

	}
} // end of class
