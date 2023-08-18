// 11:14 -

import java.util.*;
import java.io.*;

public class BOJ2210_숫자판점프 {
	static String[][] graph;
	static List<String> list = new ArrayList<>();
	static int[] numbers;
	public static void main(String[] args) throws Exception {
		// 5 x 5 , 0~9 숫자
		// 임의의 위치에서 시작, 인접 4방향으로  5번 이동하면서 
		// 서로다른 수들의 개수 -> set
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new String[5][5];
		for (int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<5; j++) {
				graph[i][j] = st.nextToken();
			}
		}
		
		for(int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				numbers = new int[6];
				dfs(i,j,0,graph[i][j]);
			}
		}
		
		System.out.println(list.size());

	}
	
	private static void dfs(int x, int y, int count, String temp) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		if (count == 5) {
			if (!(list.contains(temp))) {
				list.add(temp);
			}
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx>=0 && nx<5 && ny>=0 && ny<5) { // 범위에 들어오면
				dfs(nx,ny,count+1, temp+graph[nx][ny]);
			}
		}
	}
	
	
}
