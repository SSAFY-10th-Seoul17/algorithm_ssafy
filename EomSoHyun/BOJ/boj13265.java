import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> graph;
	static int[] color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());  // 동그라미 개수 
			int m = Integer.parseInt(st.nextToken());  // 직선들 개수 
			graph = new ArrayList<List<Integer>>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				graph.get(v1).add(v2);
				graph.get(v2).add(v1);
			}
			
			color = new int[n+1];  // 색깔 표시 (0, 1, 2)
			
			boolean flag = true;
			for (int i = 1; i <= n; i++) {
				if (color[i] == 0) {
					if (!bfs(i)) {
						flag = false;
						break;
					}
				}
			}
			
			if (flag) {
				sb.append("possible").append('\n');
			}
			else {
				sb.append("impossible").append('\n');
			}
			
		}
		
		System.out.println(sb);
		

	}
	
	
	public static boolean bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		color[i] = 1;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (Integer w : graph.get(v)) {
				if (color[w] == 0) {
					color[w] = color[v] == 1 ? 2 : 1;
					q.offer(w);
				} 
				else if (color[v] == color[w]) return false;
			}
		}
		
		return true;
		
	}
	

}
