import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] arr;
	
	static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static void bfs(int y, int x) {
		Queue<Integer> ys = new LinkedList<Integer>();
		Queue<Integer> xs = new LinkedList<Integer>();
		ys.add(y);
		xs.add(x);
		arr[y][x] = 2;
		while (!ys.isEmpty()) {
			int yy = ys.poll();
			int xx = xs.poll();
			
			for(int i = 0; i < 8; i++) {
				int yyy = yy + dy[i];
				int xxx = xx + dx[i];
				
				if (!(0 <= yyy && yyy < arr.length && 0 <= xxx && xxx < arr[yyy].length)) 
					continue;
				if (arr[yyy][xxx] == 1) {
					ys.add(yyy);
					xs.add(xxx);
					
					arr[yyy][xxx] = 2;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		arr = new int[n][m];
		
		for(int i = 0;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		int cnt = 0;
		for(int i = 0;i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
