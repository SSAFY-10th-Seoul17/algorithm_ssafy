import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] color;
	static ArrayList<ArrayList<Integer>> grid;
	
	public static int bfs() {
		int[] nowColor = new int[n];
		boolean[] visited = new boolean[n];
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		int cnt = 0;
		q.add(0);
		visited[0] = true;
		
		while (!q.isEmpty()) {
			int w = q.pollFirst();
			if (nowColor[w] != color[w] && color[w] != 0) {
				nowColor[w] = color[w];
				cnt++;
			}
			for (int i = 0; i < grid.get(w).size(); i++) {
				int v = grid.get(w).get(i);
				if (visited[v]) {
					continue;
				}
				nowColor[v] = nowColor[w];
				visited[v] = true;
				q.add(v);
			}
		}
		return cnt;
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력 받기 
		n = Integer.parseInt(br.readLine());  // 정점의 개수
		color = new int[n];  // 각 색 정보
		grid = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			grid.add(new ArrayList<Integer>());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < color.length; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			grid.get(v1-1).add(v2-1);
			grid.get(v2-1).add(v1-1);
			
		}
		
		
		System.out.println(bfs());
		
	}  // end of main
}  // end of class
