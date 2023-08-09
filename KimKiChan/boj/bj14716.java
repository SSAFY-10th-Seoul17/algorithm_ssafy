import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14716 {
	// 글자 -> 1, 글자 X -> 0
	// 1dl 상하좌우, 대각선으로 인접 -> 한개의 글자
	private static boolean[][] isVisited;
	private static int[][] arr;
	private static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
	private static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	private static int m;
	private static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		// 현수막 입력
		arr= new int[m][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVisited = new boolean[m][n];
		int count = 0;
		//조건 : isVisited == false, arr[i][j] == 1 일 경우 dfs시작. count+1
		for(int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(!isVisited[i][j] && arr[i][j] == 1) {
					dfs(i, j);
					count++;					
				}
			}
		}
		
		System.out.println(count);

	}// end of main
	
	
	private static void dfs(int x, int y) {
		isVisited[x][y] = true;
		for(int i = 0; i < 8; i++) {
			if(x+dx[i]>=0 && x+dx[i]<m && y+dy[i]>=0 && y+dy[i]<n) {
				if(!isVisited[x+dx[i]][y+dy[i]] && arr[x+dx[i]][y+dy[i]] == 1) {
					dfs(x+dx[i], y+dy[i]);
				}
			}
		}	
	}// end of dfs
	
	
}// end of class
