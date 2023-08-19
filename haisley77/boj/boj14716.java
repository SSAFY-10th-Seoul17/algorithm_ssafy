import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14716 {
	
	private static int M;
	private static int N;
	private static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
	
		arr = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					check(i,j);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	
	
	
	
	}
	
	
	static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	
	private static void check(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		arr[x][y] = 0;
		q.offer(new int[] {x,y});
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			x = info[0];
			y = info[1];
			
			for (int i = 0; i < 8; i++) {
				int px = x + dirs[i][0];
				int py = y + dirs[i][1];

				if (px >= 0 && px < M && py >= 0 && py < N && arr[px][py]==1) {
					arr[px][py] = 0;
					q.offer(new int[] {px,py});
				}
			}
		}
		return;
	}

}
